package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.CountDownLatch;
public class ExecuteTransaction implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ExecuteTransaction.class);
    private JsonNode transactionData;
    private CountDownLatch latch;
    /**
     * Default constructor for ExecuteTransaction.
     */
    public ExecuteTransaction() {}
    /**
     * Constructor for ExecuteTransaction with transaction data and a latch.
     *
     * @param transactionData The JSON representation of the transaction.
     * @param latch           A CountDownLatch for synchronization.
     */
    public ExecuteTransaction(JsonNode transactionData, CountDownLatch latch) {
        this.transactionData = transactionData;
        this.latch = latch;
    }
    /**
     * Executes the specified transaction based on its type.
     * Handles BUY, SELL, UPDATE_PRICE, and ADD_VOLUME transactions.
     * Logs a warning for invalid transaction types.
     * Decrements the latch count after completion.
     */
    @Override
    public void run() {
        try {
            String type = this.transactionData.get("type").asText();
            JsonNode data = this.transactionData.get("data");
            if(type.equals("BUY")) {
                String coin = data.get("coin").asText();
                long quantity = data.get("quantity").asLong();
                String walletAddress = data.get("wallet_address").asText();
                TradingSystem.executeBuy(coin, quantity, walletAddress);
            }
            else if(type.equals("SELL")) {
                String coin = data.get("coin").asText();
                long quantity = data.get("quantity").asLong();
                String walletAddress = data.get("wallet_address").asText();
                TradingSystem.executeSell(coin, quantity, walletAddress);
            }
            else if(type.equals("UPDATE_PRICE")) {
                String coin = data.get("coin").asText();
                double price = data.get("price").asDouble();
                TradingSystem.executeUpdatePrice(coin, price);
            }
            else if(type.equals("ADD_VOLUME")) {
                String coin = data.get("coin").asText();
                long volume = data.get("volume").asLong();
                TradingSystem.executeAddVolume(coin, volume);
            }
            else {
                logger.warn("Invalid type");
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        latch.countDown();
    }
}