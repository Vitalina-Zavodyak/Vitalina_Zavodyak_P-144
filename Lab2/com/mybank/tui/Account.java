package com.mybank.tui;

public class Account {
    private String accountType;
    private double balance;
    private double additionalValue;

    public Account(String accountType, double balance, double additionalValue) {
        this.accountType = accountType;
        this.balance = balance;
        this.additionalValue = additionalValue;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public double getAdditionalValue() {
        return additionalValue;
    }
}