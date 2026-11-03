
package com.mybank.gui;

import com.mybank.domain.Bank;
import com.mybank.domain.Customer;
import com.mybank.domain.Account;
import com.mybank.domain.CheckingAccount;
import com.mybank.domain.SavingsAccount;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Locale;

import javax.swing.*;

public class SWINGDemo {

    private final JEditorPane log;
    private final JButton show;
    private final JButton report;
    private final JComboBox<String> clients;

    public SWINGDemo() {
        log = new JEditorPane("text/html", "");
        log.setEditable(false);
        log.setPreferredSize(new Dimension(500, 300));

        show = new JButton("Show");
        report = new JButton("Report");
        clients = new JComboBox<>();

        loadData();

        for (int i = 0; i < Bank.getNumberOfCustomers(); i++) {
            Customer customer = Bank.getCustomer(i);
            clients.addItem(
                customer.getLastName() + ", " + customer.getFirstName()
            );
        }
    }

    private void loadData() {
        try (Scanner scanner = new Scanner(new File("test.dat"))) {

            // Використовуємо крапку для десяткових чисел
            scanner.useLocale(Locale.US);

            int numberOfCustomers = scanner.nextInt();

            for (int i = 0; i < numberOfCustomers; i++) {
                String firstName = scanner.next();
                String lastName = scanner.next();
                int numberOfAccounts = scanner.nextInt();

                Bank.addCustomer(firstName, lastName);
                Customer customer = Bank.getCustomer(i);

                for (int j = 0; j < numberOfAccounts; j++) {
                    String type = scanner.next();
                    double balance = scanner.nextDouble();
                    double additionalValue = scanner.nextDouble();

                    if (type.equalsIgnoreCase("C")) {
                        customer.addAccount(
                            new CheckingAccount(balance, additionalValue)
                        );
                    } else if (type.equalsIgnoreCase("S")) {
                        customer.addAccount(
                            new SavingsAccount(balance, additionalValue)
                        );
                    }
                }
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(
                null, "File test.dat not found!"
            );
        }
    }

    private void showCustomer() {
        int index = clients.getSelectedIndex();

        if (index < 0) {
            return;
        }

        Customer customer = Bank.getCustomer(index);

        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h2>")
            .append(customer.getLastName()).append(", ")
            .append(customer.getFirstName())
            .append("</h2><hr>");

        for (int i = 0; i < customer.getNumberOfAccounts(); i++) {
            Account account = customer.getAccount(i);

            String type = account instanceof CheckingAccount
                    ? "Checking Account" : "Savings Account";

            html.append("<b>Account type:</b> ")
                .append(type)
                .append("<br><b>Balance:</b> $")
                .append(String.format(Locale.US, "%.2f",
                        account.getBalance()))
                .append("<br><br>");
        }

        html.append("</body></html>");
        log.setText(html.toString());
    }

    private void showReport() {
        StringBuilder html = new StringBuilder();

        html.append("<html><body>");
        html.append("<h2>MyBank - Customer Report</h2><hr>");

        for (int i = 0; i < Bank.getNumberOfCustomers(); i++) {
            Customer customer = Bank.getCustomer(i);

            html.append("<h3>")
                .append(customer.getLastName()).append(", ")
                .append(customer.getFirstName())
                .append("</h3>");

            double total = 0;

            for (int j = 0; j < customer.getNumberOfAccounts(); j++) {
                Account account = customer.getAccount(j);
                total += account.getBalance();

                String type = account instanceof CheckingAccount
                        ? "Checking Account" : "Savings Account";

                html.append(type)
                    .append(" — $")
                    .append(String.format(Locale.US, "%.2f",
                            account.getBalance()))
                    .append("<br>");
            }

            html.append("<b>Total balance: $")
                .append(String.format(Locale.US, "%.2f", total))
                .append("</b><hr>");
        }

        html.append("</body></html>");
        log.setText(html.toString());
    }

    private void launchFrame() {
        JFrame frame = new JFrame("MyBank clients");
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 3));

        topPanel.add(clients);
        topPanel.add(show);
        topPanel.add(report);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(log), BorderLayout.CENTER);

        show.addActionListener(e -> showCustomer());
        report.addActionListener(e -> showReport());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SWINGDemo demo = new SWINGDemo();
            demo.launchFrame();
        });
    }
}
