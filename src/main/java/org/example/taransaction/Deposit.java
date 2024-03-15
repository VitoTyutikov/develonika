package org.example.taransaction;

import java.time.LocalDateTime;

public class Deposit extends Transaction {
    public Deposit(LocalDateTime date, double amount) {
        super(date, amount);
    }

}
