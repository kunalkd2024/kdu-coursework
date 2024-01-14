package org.example;

/**
 * The {@code Coin} class represents a cryptocurrency with attributes like rank, name, symbol, price,
 * and circulating supply. It provides methods to access and modify these attributes.
 */
public class Coin {
    private int rank;
    private String name;
    private String symbol;
    private double price;
    private long circulatingSupply;
    /**
     * Constructs a new {@code Coin} instance with the specified attributes.
     *
     * @param rank             The rank of the cryptocurrency.
     * @param name             The name of the cryptocurrency.
     * @param symbol           The symbol of the cryptocurrency.
     * @param price            The price of the cryptocurrency.
     * @param circulatingSupply The circulating supply of the cryptocurrency.
     */
    public Coin(int rank, String name, String symbol, double price, long circulatingSupply) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
    }
    public int getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCirculatingSupply() {
        return circulatingSupply;
    }

    public void updateCS(long quantity) {
        this.circulatingSupply += quantity;
    }
    /**
     * Returns a string representation of the {@code Coin} object.
     *
     * @return A string representation of the {@code Coin} object.
     */
    @Override
    public String toString() {
        return "Coin{" +
                ", rank=" + rank +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                ", circulatingSupply=" + circulatingSupply +
                '}';
    }
}
