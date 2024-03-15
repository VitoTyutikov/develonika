package org.example.bank;

import java.util.Scanner;

public class BankReader {
    private final Scanner scanner;


    public BankReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readSelect() {
        System.out.print("Выберите действие (введите номер): ");
        return scanner.nextInt();
    }

    public int readDepositAmount() {
        System.out.print("Введите сумму для пополнения: ");
        return scanner.nextInt();
    }

    public int readWithdrawAmount() {
        System.out.print("Введите сумму для снятия: ");
        return scanner.nextInt();
    }
}
