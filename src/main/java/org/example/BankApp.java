package org.example;

import org.example.taransaction.Deposit;
import org.example.taransaction.Transaction;
import org.example.taransaction.Withdraw;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankApp {
    private final Scanner scanner;
    private final Account account;

    public BankApp() {
        this.scanner = new Scanner(System.in);
        this.account = new Account();
    }

    public void run() {
        System.out.println("Добро пожаловать в приложение \"Банковский счет\"!\n" +
                "1. Пополнить счет\n" +
                "2. Снять деньги\n" +
                "3. Проверить баланс\n" +
                "4. История транзакций\n" +
                "5. Выход\n");
        boolean running = true;
        while (running) {
            System.out.print("Выберите действие (введите номер): ");
            int select;
            try {
                select = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Необходимо вводить только числа");
                scanner.nextLine();
                continue;
            }
            switch (select) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    balance();
                    break;
                case 4:
                    transactionHistory();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Попробуйте снова");
            }
        }
        System.out.println("До свидания!");
        scanner.close();
    }


    private void deposit() {
        System.out.print("Введите сумму для пополнения: ");
        try {
            int amount = scanner.nextInt();
            account.deposit(amount);
            System.out.println("Счет успешно пополнен на " + amount + " руб");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Необходимо вводить только числа");
        }
    }

    private void withdraw() {
        System.out.print("Введите сумму для снятия: ");
        try {
            int amount = scanner.nextInt();
            account.withdraw(amount);
            System.out.println("Со счета снято " + amount + " руб. Баланс: " + account.getBalance() + " руб.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Необходимо вводить только числа");
        }
    }

    private void balance() {
        System.out.println("Баланс на счете: " + account.getBalance() + " руб.");
    }

    private void transactionHistory() {
        System.out.println("История транзакций:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (Transaction transaction : account.getTransactions()) {
            if (transaction instanceof Deposit) {
                System.out.println("Пополнение: +" + transaction.getAmount() + " руб (" + transaction.getDate().format(formatter) + ")");
            } else if (transaction instanceof Withdraw) {
                System.out.println("Снятие: -" + transaction.getAmount() + " руб (" + transaction.getDate().format(formatter) + ")");
            }
        }
    }
}
