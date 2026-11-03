
package com.mybank.tui;

import java.util.LinkedList;
import java.util.List;

import org.jline.reader.*;
import org.jline.reader.impl.completer.*;
import org.jline.utils.*;
import org.fusesource.jansi.*;

public class CLIdemo {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private String[] commandsList;
    private Bank bank = new Bank();

    public void init() {
        commandsList = new String[]{
            "help", "customers", "customer", "report", "exit"
        };

        try {
            bank.loadFromFile("D:\\Java\\TUIDemo\\test.dat");
        } catch (Exception e) {
            System.out.println("Error loading test.dat: " + e.getMessage());
        }
    }

    public void run() {
        AnsiConsole.systemInstall();

        printWelcomeMessage();

        LineReaderBuilder readerBuilder = LineReaderBuilder.builder();
        List<Completer> completors = new LinkedList<>();

        completors.add(new StringsCompleter(commandsList));
        readerBuilder.completer(new ArgumentCompleter(completors));

        LineReader reader = readerBuilder.build();

        String line;

        while ((line = readLine(reader, "")) != null) {

            if ("help".equals(line)) {
                printHelp();

            } else if ("customers".equals(line)) {

                System.out.println("\nList of bank customers:");
                System.out.println("Customer number\tName\t\tAccount balance");
                System.out.println("---------------------------------------------");

                for (Customer customer : bank.getCustomers()) {
                    Account account = customer.getFirstAccount();

                    if (account != null) {
                        System.out.println(
                            customer.getCustomerNumber() + "\t\t"
                            + customer.getFullName() + "\t$"
                            + account.getBalance()
                        );
                    } else {
                        System.out.println(
                            customer.getCustomerNumber() + "\t\t"
                            + customer.getFullName() + "\tNo account"
                        );
                    }
                }

            } else if (line.startsWith("customer")) {

                try {
                    String[] parts = line.split("\\s+");

                    if (parts.length != 2) {
                        System.out.println(
                            ANSI_RED + "Use: customer <number>" + ANSI_RESET
                        );
                        continue;
                    }

                    int custNo = Integer.parseInt(parts[1]);
                    Customer cust = bank.getCustomer(custNo);

                    if (cust == null) {
                        System.out.println(
                            ANSI_RED + "ERROR! Wrong customer number!" + ANSI_RESET
                        );
                        continue;
                    }

                    Account account = cust.getFirstAccount();

                    System.out.println("\nCustomer #" + custNo);
                    System.out.println("Name: " + cust.getFullName());

                    if (account != null) {
                        System.out.println(
                            "Account type: " + account.getAccountType()
                        );
                        System.out.println(
                            "Balance: $" + account.getBalance()
                        );
                    } else {
                        System.out.println("Account: No account");
                    }

                } catch (NumberFormatException e) {
                    System.out.println(
                        ANSI_RED + "ERROR! Customer number must be a number!"
                        + ANSI_RESET
                    );
                }

            } else if ("report".equals(line)) {

                System.out.println("\n========== CUSTOMER REPORT ==========");

                for (Customer customer : bank.getCustomers()) {
                    System.out.println("\nCustomer: " + customer.getFullName());
                    System.out.println(
                        "Customer number: " + customer.getCustomerNumber()
                    );

                    Account account = customer.getFirstAccount();

                    if (account != null) {
                        System.out.println(
                            "Account type: " + account.getAccountType()
                        );
                        System.out.println(
                            "Balance: $" + account.getBalance()
                        );
                    } else {
                        System.out.println("No accounts");
                    }

                    System.out.println("-------------------------------------");
                }

            } else if ("exit".equals(line)) {

                System.out.println("Exiting application");
                AnsiConsole.systemUninstall();
                return;

            } else {

                System.out.println(
                    ANSI_RED
                    + "Invalid command. Press TAB or type help."
                    + ANSI_RESET
                );
            }
        }

        AnsiConsole.systemUninstall();
    }

    private void printWelcomeMessage() {
        System.out.println(
            "\nWelcome to " + ANSI_GREEN
            + "MyBank Console Client App"
            + ANSI_RESET
            + "!\nFor assistance press TAB or type help then ENTER."
        );
    }

    private void printHelp() {
        System.out.println("\nAvailable commands:");
        System.out.println("help              - Show help");
        System.out.println("customers         - Show all customers");
        System.out.println("customer <number> - Show customer details");
        System.out.println("report            - Show customer report");
        System.out.println("exit              - Exit the application");
    }

    private String readLine(LineReader reader, String promptMessage) {
        try {
            String line = reader.readLine(
                promptMessage + ANSI_YELLOW + "\nbank> " + ANSI_RESET
            );
            return line.trim();

        } catch (UserInterruptException e) {
            return null;

        } catch (EndOfFileException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        CLIdemo shell = new CLIdemo();
        shell.init();
        shell.run();
    }
}
