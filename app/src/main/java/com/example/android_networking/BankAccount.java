package com.example.android_networking;

public class BankAccount {
    //Khai báo biến
    private String accountNumber;
    private double balance;

    //Constructor
    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    //Getter
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    //Hàm gửi tiền
    public synchronized void deposit(double amount) {
        balance += amount;
    }

    //Hàm rút tiền
    public synchronized boolean withdraw(double amount) {
        balance -= amount;
        return balance >= 0;
    }
}

