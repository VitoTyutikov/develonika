package org.example.bank;

import org.example.taransaction.Deposit;
import org.example.taransaction.Transaction;
import org.example.taransaction.Withdraw;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankEventListener {
    public void welcomeMessage() {
        System.out.println("Добро пожаловать в приложение \"Банковский счет\"!\n" +
                "1. Пополнить счет\n" +
                "2. Снять деньги\n" +
                "3. Проверить баланс\n" +
                "4. История транзакций\n" +
                "5. Выход\n");
    }

    public void tryAgain() {
        System.out.println("Попробуйте снова");
    }

    public void goodbye() {
        System.out.println("До свидания!");
    }

    public void incorrectInput() {
        System.out.println("Необходимо вводить только числа");
    }

    public void onSuccessfulDeposit(int amount) {
        System.out.println("Счет успешно пополнен на " + amount + " руб");
    }

    public void onSuccessfulWithdraw(int amount, double balance) {
        System.out.println("Со счета снято " + amount + " руб. Баланс: " + balance + " руб.");
    }

    public void balance(double balance) {
        System.out.println("Баланс на счете: " + balance + " руб.");
    }

    public void transactionHistory(List<Transaction> transactions) {
        System.out.println("История транзакций:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction instanceof Deposit) {
                System.out.println(i + 1 + ". Пополнение: +" + transaction.getAmount() + " руб (" + transaction.getDate().format(formatter) + ")");
            } else if (transaction instanceof Withdraw) {
                System.out.println(i + 1 + ". Снятие: -" + transaction.getAmount() + " руб (" + transaction.getDate().format(formatter) + ")");
            }
        }

    }
}
