package org.example.taransaction;

import java.time.LocalDateTime;

public class Withdraw extends Transaction {
    public Withdraw(LocalDateTime date, double amount) {
        super(date, amount);
    }
}
