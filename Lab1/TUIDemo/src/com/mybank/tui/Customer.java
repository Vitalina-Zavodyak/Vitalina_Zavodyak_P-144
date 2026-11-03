package com.mybank.tui;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerNumber;
    private String firstName;
    private String lastName;
    private List<Account> accounts = new ArrayList<>();

    public Customer(int customerNumber, String firstName, String lastName) {
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getFirstAccount() {
        if (accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }
}