package edu.lysak.java8Training;

import java.util.UUID;

public class Transaction {
    private UUID uid;
    private double price;
    private String currency;

    public Transaction() {
        super();
    }

    public Transaction(String currency) {
        uid = UUID.randomUUID();
        this.currency = currency;
    }

    public Transaction(double price, String currency) {
        this(currency);
        this.price = price;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Transaction [price=" + price + ", currency=" + currency + "]";
    }

}