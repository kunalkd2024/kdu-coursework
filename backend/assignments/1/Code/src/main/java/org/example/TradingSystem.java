package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The {@code TradingSystem} class represents a system for executing cryptocurrency trading operations
 * and managing related information such as coins, traders, and transactions.
 */
public class TradingSystem {
    TradingSystem(){}
    private static final Logger logger = LoggerFactory.getLogger(TradingSystem.class);
    private static final Lock coinsLock = new ReentrantLock();
    private static final Lock traderLock = new ReentrantLock();

    protected static Map<String, Coin> symbolToCoin = new HashMap<>();
    protected static Map<String, String> coinNameToSymbol = new HashMap<>();
    protected static Map<String, Trader> walletToTrader = new HashMap<>();
    protected static Map<String, Map<String, List<DataLoader.Pair<Double, Long>>>> walletCoinsTrading = new HashMap<>();
    protected static Set<Coin> coinsSet = new TreeSet<>(Comparator.comparing(Coin::getPrice).reversed());

    /**
     * Executes a buy operation for a specified cryptocurrency symbol, quantity, and wallet address.
     *
     * @param symbol        The symbol of the cryptocurrency.
     * @param quantity      The quantity of the cryptocurrency to buy.
     * @param walletAddress The wallet address of the trader.
     */
    public static void executeBuy(String symbol, long quantity, String walletAddress) {
        Coin coins = symbolToCoin.get(symbol);
        Trader trader = walletToTrader.get(walletAddress);

        if (coins != null && trader != null) {
            executeWithLocks(coinsLock, traderLock, () -> {
                if (coins.getCirculatingSupply() >= quantity) {
                    double price = coins.getPrice();
                    coins.updateCS(-quantity);
                    walletCoinsTrading.putIfAbsent(walletAddress, new HashMap<>());
                    walletCoinsTrading.get(walletAddress).putIfAbsent(symbol, new ArrayList<>());
                    walletCoinsTrading.get(walletAddress).get(symbol).add(new DataLoader.Pair<>(-price,quantity));
                    trader.updatePortFolio(symbol, quantity);
                }
            });
        }
    }

    /**
     * Executes a sell operation for a specified cryptocurrency symbol, quantity, and wallet address.
     *
     * @param symbol        The symbol of the cryptocurrency.
     * @param quantity      The quantity of the cryptocurrency to sell.
     * @param walletAddress The wallet address of the trader.
     */
    public static void executeSell(String symbol, long quantity, String walletAddress) {
        Coin coins = symbolToCoin.get(symbol);
        Trader trader = walletToTrader.get(walletAddress);

        if (coins != null && trader != null && trader.getPortfolio().containsKey(symbol)) {
            executeWithLocks(coinsLock, traderLock, () -> {
                if (trader.getPortfolio().get(symbol).getFirst() >= quantity) {
                    trader.updatePortFolio(symbol, -quantity);
                    double price = coins.getPrice();
                    coins.updateCS(quantity);
                    walletCoinsTrading.putIfAbsent(walletAddress, new HashMap<>());
                    walletCoinsTrading.get(walletAddress).putIfAbsent(symbol, new ArrayList<>());
                    walletCoinsTrading.get(walletAddress).get(symbol).add(new DataLoader.Pair<>(price,quantity));
                }
            });
        }
    }

    /**
     * Executes an update operation for the price of a specified cryptocurrency symbol.
     *
     * @param symbol The symbol of the cryptocurrency.
     * @param price  The updated price of the cryptocurrency.
     */
    public static void executeUpdatePrice(String symbol, double price) {
        Coin coins = symbolToCoin.get(symbol);
        if (coins != null) {
            executeWithLock(coinsLock, () -> {
                coinsSet.remove(coins);
                coins.setPrice(price);
                coinsSet.add(coins);
            });
        }
    }

    /**
     * Executes an add volume operation for a specified cryptocurrency symbol.
     *
     * @param symbol The symbol of the cryptocurrency.
     * @param volume The volume to add to the circulating supply of the cryptocurrency.
     */
    public static void executeAddVolume(String symbol, long volume) {
        Coin coins = symbolToCoin.get(symbol);
        if (coins != null) {
            executeWithLock(coinsLock, () -> coins.updateCS(volume));
        }
    }

    private static void executeWithLock(Lock lock, Runnable action) {
        lock.lock();
        try {
            action.run();
        } finally {
            lock.unlock();
        }
    }

    private static void executeWithLocks(Lock lock1, Lock lock2, Runnable action) {
        lock1.lock();
        try {
            lock2.lock();
            try {
                action.run();
            } finally {
                lock2.unlock();
            }
        } finally {
            lock1.unlock();
        }
    }

    /**
     * Displays details of a cryptocurrency coin based on either its code name or symbol.
     * @param codeName The code name or symbol of the cryptocurrency.
     */
    public static void displayCoinDetails(String codeName) {
        Coin coins = getCoinsByCodeName(codeName);
        if (coins != null) {
            logger.info("Displaying coin details for codeName: {}", coins);
        }
    }

    /**
     * Displays details of the top N coins in the set, ordered by price.
     * @param n The number of top coins to display.
     */
    public static void displayTopNCoins(int n) {
        int i = 0;
        for (Coin coins : coinsSet) {
            if (i < n) {
                logger.info("Coin :: {} ", coins);
            }
            i++;
            if (i == n) {
                break;
            }
        }
    }

    /**
     * Displays the portfolio of a trader based on their wallet address.
     * @param walletAddress The wallet address of the trader.
     */
    public static void displayPortfolio(String walletAddress) {
        Trader trader = walletToTrader.get(walletAddress);
        if (trader != null) {
            trader.getPortfolio().forEach((symbol, quantity) ->
                    logger.info("{} :: {}", symbol, quantity));
        }
    }

    /**
     * Displays profit or loss for a trader based on their wallet address.
     * @param walletAddress The wallet address of the trader.
     */
    public static void displayProfitLossForTrader(String walletAddress) {
        if (walletCoinsTrading.containsKey(walletAddress)) {
            walletCoinsTrading.get(walletAddress).forEach((symbol, transactions) -> {
                double buyAmount = 0.0D;
                double sellAmount = 0.0D;
                long remainingQuantity = 0;
                for (DataLoader.Pair<Double, Long> pair : transactions) {
                    if (pair.getFirst() < 0) {
                        buyAmount += pair.getFirst() * pair.getSecond();
                        remainingQuantity += pair.getSecond();
                    } else {
                        sellAmount += pair.getFirst() * pair.getSecond();
                        remainingQuantity -= pair.getSecond();
                    }
                }
                if (Double.compare(sellAmount, 0.0D) == 0) {
                    logger.info("No sell order placed yet for coin :: {}", symbol);
                } else {
                    double profitLoss = (sellAmount + buyAmount) + (remainingQuantity * symbolToCoin.get(symbol).getPrice());
                    logger.info("Coin :: {} and Profit/Loss :: {}", symbol, profitLoss);
                }
            });
        }
    }
    private static Coin getCoinsByCodeName(String codeName) {
        if (symbolToCoin.containsKey(codeName)) {
            return symbolToCoin.get(codeName);
        } else {
            String symbol = coinNameToSymbol.get(codeName);
            return symbolToCoin.get(symbol);
        }
    }
}
