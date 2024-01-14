package org.example;

import java.util.HashMap;
import java.util.Map;
/**
 * The {@code Trader} class represents a cryptocurrency trader with associated personal and portfolio information.
 */
public class Trader {
    private String firstName;
    private String lastName;
    private String phone;
    private String walletAddress;
    private Map<String, DataLoader.Pair<Long, Double>> portfolio;

    /**
     * Constructs a new trader with the specified personal information.
     *
     * @param firstName     The first name of the trader.
     * @param lastName      The last name of the trader.
     * @param phone         The phone number of the trader.
     * @param walletAddress The wallet address of the trader.
     */
    public Trader(String firstName, String lastName, String phone, String walletAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.walletAddress = walletAddress;
        this.portfolio = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    /**
     * Updates the trader's portfolio with the specified quantity for a given symbol.
     * If the symbol already exists in the portfolio, the quantity is added to the existing quantity.
     * If the symbol is not present, a new entry is created in the portfolio.
     *
     * @param symbol   The symbol of the cryptocurrency.
     * @param quantity The quantity of the cryptocurrency to update in the portfolio.
     */
    public void updatePortFolio(String symbol, long quantity) {
        if (this.portfolio.containsKey(symbol)) {
            DataLoader.Pair<Long, Double> pair = this.portfolio.get(symbol);
            pair.setFirst(pair.getFirst() + quantity);
            this.portfolio.put(symbol, pair);
        } else {
            this.portfolio.put(symbol, new DataLoader.Pair<>(quantity, 0D));
        }
    }

    public Map<String, DataLoader.Pair<Long, Double>> getPortfolio() {
        return portfolio;
    }

    /**
     * Returns a string representation of the trader, including personal information.
     *
     * @return A string representation of the trader.
     */
    @Override
    public String toString() {
        return "Trader{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", walletAddress='" + walletAddress + '\'' +
                '}';
    }
}
