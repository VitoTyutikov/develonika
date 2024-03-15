package org.example;

import org.example.taransaction.Deposit;
import org.example.taransaction.Transaction;
import org.example.taransaction.Withdraw;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private int lastUpdatedTransactionIndex;
    private final List<Transaction> transactions;


    public Account() {
        this.balance = 0.0;
        this.lastUpdatedTransactionIndex = -1;
        this.transactions = new ArrayList<>();
    }

    private void updateBalance() {
        for (int i = lastUpdatedTransactionIndex + 1; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction instanceof Deposit) {
                balance += transaction.getAmount();
            } else if (transaction instanceof Withdraw) {
                balance -= transaction.getAmount();
            }
            lastUpdatedTransactionIndex = i;
        }
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма пополнения должна быть положительной");
        }
        transactions.add(new Deposit(LocalDateTime.now(), amount));
    }


    public void withdraw(double amount) {
        updateBalance();
        if (amount <= 0 || balance < amount) {
            throw new IllegalArgumentException("Сумма снятия должна быть положительной и не превышать баланс");
        }
        transactions.add(new Withdraw(LocalDateTime.now(), amount));
    }

    public double getBalance() {
        updateBalance();
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
