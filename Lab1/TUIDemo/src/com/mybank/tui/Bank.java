package com.mybank.tui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale; 

public class Bank {
    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomer(int customerNumber) {
        for (Customer customer : customers) {
            if (customer.getCustomerNumber() == customerNumber) {
                return customer;
            }
        }
        return null;
    }

    public void loadFromFile(String filename) throws FileNotFoundException {
        customers.clear();

        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.useLocale(Locale.US);

int customerCount = scanner.nextInt();
            for (int i = 1; i <= customerCount; i++) {
                String firstName = scanner.next();
                String lastName = scanner.next();
                int accountCount = scanner.nextInt();

                Customer customer = new Customer(i, firstName, lastName);

                for (int j = 0; j < accountCount; j++) {
                    String type = scanner.next();
                    double balance = scanner.nextDouble();
                    double additionalValue = scanner.nextDouble();

                    customer.addAccount(
                        new Account(type, balance, additionalValue)
                    );
                }

                               addCustomer(customer);
            }
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}