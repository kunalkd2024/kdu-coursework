package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * The {@code Main} class serves as the entry point for the cryptocurrency trading system application.
 * It contains methods to load data from CSV and JSON, execute transactions, and display information.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * The main method of the application that initializes and executes various components of the trading system.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        loadFromCSVcoins("src/test/resources/coins.csv");
        loadFromCSVtraders("src/test/resources/traders.csv");


        JsonNode jsonTransactions = loadFromJson("src/test/resources/test_transaction.json");
        if (jsonTransactions != null) {
            CountDownLatch latch = new CountDownLatch(jsonTransactions.size());
            executeTransactions(jsonTransactions, latch);

            try {
                latch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error(String.valueOf(e));
            }
        }
    }

    /**
     * Loads data from a JSON file into a {@code JsonNode}.
     *
     * @return A {@code JsonNode} containing the loaded data.
     */
    public static JsonNode loadFromJson(String path) {
        JsonNode jsonNode = null;
        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(path);

            if (inputStream != null) {
                jsonNode = new ObjectMapper().readTree(inputStream);
            } else {
                logger.error("Could not find the file: " + path);
            }
        } catch (IOException e) {
            logger.error("Error reading JSON file: {}", e.getMessage());
        }
        return jsonNode;
    }

    /**
     * Loads data from CSV files into the trading system, including coins and traders.
     */
    public static ArrayList<String[]> loadFromCSVcoins(String path) {
        List<Coin> coins = loadCoins(path);
        for (Coin coin : coins) {
            TradingSystem.symbolToCoin.put(coin.getSymbol(), coin);
            TradingSystem.coinNameToSymbol.put(coin.getName(), coin.getSymbol());
            TradingSystem.coinsSet.add(coin);
        }
        return new ArrayList<String[]>();
    }

    public static ArrayList<String[]> loadFromCSVtraders(String path) {
        List<Trader> traders = loadTraders(path);
        for (Trader trader : traders) {
            TradingSystem.walletToTrader.put(trader.getWalletAddress(), trader);
        }
        return new ArrayList<String[]>();
    }

    /**
     * Loads coin data from a CSV file and converts it into a list of {@code Coin} objects.
     *
     * @return A list of {@code Coin} objects loaded from the CSV file.
     */
    private static List<Coin> loadCoins(String path) {
        try {
            List<CSVRecord> coinsData = (List<CSVRecord>) DataLoader.loadData(path, DataLoader.FileType.CSV);
            return convertToCoins(coinsData);
        } catch (Exception e) {
            logger.error("Error loading coins from CSV: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Loads trader data from a CSV file and converts it into a list of {@code Trader} objects.
     *
     * @return A list of {@code Trader} objects loaded from the CSV file.
     */
    private static List<Trader> loadTraders(String path) {
        try {
            List<CSVRecord> tradersData = (List<CSVRecord>) DataLoader.loadData(path, DataLoader.FileType.CSV);
            return convertToTraders(tradersData);
        } catch (Exception e) {
            logger.error("Error loading traders from CSV: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Converts a list of CSV records into a list of {@code Coin} objects.
     *
     * @param csvRecords The list of CSV records containing coin data.
     * @return A list of {@code Coin} objects.
     */
    private static List<Coin> convertToCoins(List<CSVRecord> csvRecords) {
        List<Coin> coins = new ArrayList<>();
        for (CSVRecord rec : csvRecords) {
            int rank = Integer.parseInt(rec.get("Rank"));
            String name = rec.get("Name");
            String symbol = rec.get("Symbol");
            double price = Double.parseDouble(rec.get("Price"));
            long circulatingSupply = Long.parseLong(rec.get("Circulating Supply"));

            Coin coin = new Coin(rank, name, symbol, price, circulatingSupply);
            coins.add(coin);
        }
        return coins;
    }

    /**
     * Converts a list of CSV records into a list of {@code Trader} objects.
     *
     * @param csvRecords The list of CSV records containing trader data.
     * @return A list of {@code Trader} objects.
     */
    private static List<Trader> convertToTraders(List<CSVRecord> csvRecords) {
        List<Trader> traders = new ArrayList<>();
        for (CSVRecord rec : csvRecords) {
            String firstName = rec.get("first_name");
            String lastName = rec.get("last_name");
            String phone = rec.get("phone");
            String walletAddress = rec.get("Wallet Address");

            Trader trader = new Trader(firstName, lastName, phone, walletAddress);
            traders.add(trader);
        }
        return traders;
    }

    /**
     * Executes transactions based on a list of JSON transactions and a {@code CountDownLatch}.
     *
     * @param jsonTransactions The list of JSON transactions to execute.
     * @param latch            A {@code CountDownLatch} for synchronization.
     */
    public static void executeTransactions(JsonNode jsonTransactions, CountDownLatch latch) {
        for (JsonNode transaction : jsonTransactions) {
            ExecuteTransaction executeTransaction = new ExecuteTransaction(transaction, latch);
            executeTransaction.run();
        }
        TradingSystem.displayTopNCoins(8);
        TradingSystem.displayCoinDetails("LUNA");
        TradingSystem.displayCoinDetails("Terra");
        String walletAddress = "0x1397ffcfbd2badb81a0734035a957ef1";
        TradingSystem.displayPortfolio(walletAddress);
        logger.info(TradingSystem.walletCoinsTrading.getOrDefault(walletAddress, new HashMap<>()).toString());
        TradingSystem.displayProfitLossForTrader(walletAddress);
    }

}