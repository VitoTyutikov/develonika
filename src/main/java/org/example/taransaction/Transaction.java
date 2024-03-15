package org.example.taransaction;

import java.time.LocalDateTime;

public abstract class Transaction {
    protected LocalDateTime date;
    protected double amount;

    public Transaction(LocalDateTime date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
