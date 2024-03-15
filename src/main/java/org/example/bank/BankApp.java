package org.example.bank;

import org.example.Account;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankApp {
    private final Scanner scanner;
    private final Account account;
    private final BankReader bankReader;
    private final BankEventListener bankEventListener;

    public BankApp() {
        this.scanner = new Scanner(System.in);
        this.account = new Account();
        this.bankReader = new BankReader(scanner);
        this.bankEventListener = new BankEventListener();

    }

    public void run() {
        bankEventListener.welcomeMessage();
        boolean running = true;
        while (running) {
            int select;
            try {
                select = bankReader.readSelect();
            } catch (InputMismatchException e) {
                bankEventListener.incorrectInput();
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
                    bankEventListener.tryAgain();
            }
        }
        bankEventListener.goodbye();
        scanner.close();
    }


    private void deposit() {
        try {
            int amount = bankReader.readDepositAmount();
            account.deposit(amount);
            bankEventListener.onSuccessfulDeposit(amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            bankEventListener.incorrectInput();
            scanner.nextLine();
        }
    }

    private void withdraw() {
        try {
            int amount = bankReader.readWithdrawAmount();
            account.withdraw(amount);
            bankEventListener.onSuccessfulWithdraw(amount, account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            bankEventListener.incorrectInput();
            scanner.nextLine();
        }
    }

    private void balance() {
        bankEventListener.balance(account.getBalance());
    }

    private void transactionHistory() {
        bankEventListener.transactionHistory(account.getTransactions());
    }
}
