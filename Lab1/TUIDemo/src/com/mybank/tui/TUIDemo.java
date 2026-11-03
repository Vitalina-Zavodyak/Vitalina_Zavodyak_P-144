package com.mybank.tui;

import jexer.TAction;
import jexer.TApplication;
import jexer.TField;
import jexer.TText;
import jexer.TWindow;
import jexer.event.TMenuEvent;
import jexer.menu.TMenu;

/**
 *
 * @author Alexander 'Taurus' Babich
 */
public class TUIDemo extends TApplication {
    private Bank bank = new Bank();

    private static final int ABOUT_APP = 2000;
    private static final int CUST_INFO = 2010;

    public static void main(String[] args) throws Exception {
        TUIDemo tdemo = new TUIDemo();
        (new Thread(tdemo)).start();
    }

    public TUIDemo() throws Exception { 
        super(BackendType.SWING);
        
    try {
    bank.loadFromFile("test.dat");
} catch (Exception e) {
    System.out.println("Cannot load test.dat: " + e.getMessage());
}

        addToolMenu();
        //custom 'File' menu
        TMenu fileMenu = addMenu("&File");
        fileMenu.addItem(CUST_INFO, "&Customer Info");
        fileMenu.addDefaultItem(TMenu.MID_SHELL);
        fileMenu.addSeparator();
        fileMenu.addDefaultItem(TMenu.MID_EXIT);
        //end of 'File' menu  

        addWindowMenu();

        //custom 'Help' menu
        TMenu helpMenu = addMenu("&Help");
        helpMenu.addItem(ABOUT_APP, "&About...");
        //end of 'Help' menu 

        setFocusFollowsMouse(true);
        //Customer window
        ShowCustomerDetails();
    }

    @Override
    protected boolean onMenu(TMenuEvent menu) {
        if (menu.getId() == ABOUT_APP) {
            messageBox("About", "\t\t\t\t\t   Just a simple Jexer demo.\n\nCopyright \u00A9 2019 Alexander \'Taurus\' Babich").show();
            return true;
        }
        if (menu.getId() == CUST_INFO) {
            ShowCustomerDetails();
            return true;
        }
        return super.onMenu(menu);
    }

    private void ShowCustomerDetails() {
        TWindow custWin = addWindow("Customer Window", 2, 1, 40, 10, TWindow.NOZOOMBOX);
        custWin.newStatusBar("Enter valid customer number and press Show...");

        custWin.addLabel("Enter customer number: ", 2, 2);
        TField custNo = custWin.addField(24, 2, 3, false);
        TText details = custWin.addText("Owner Name: \nAccount Type: \nAccount Balance: ", 2, 4, 38, 8);
        custWin.addButton("&Show", 28, 2, new TAction() {
            @Override
            public void DO() {
                try {
                    int custNum = Integer.parseInt(custNo.getText());
                    //details about customer with index==custNum
                    Customer customer = bank.getCustomer(custNum);

if (customer == null) {
    messageBox("Error", "Customer not found!").show();
    return;
}

Account account = customer.getFirstAccount();

if (account == null) {
    details.setText("Owner Name: " + customer.getFullName()
            + "\nAccount Type: No account"
            + "\nAccount Balance: 0.00");
} else {
    details.setText("Owner Name: " + customer.getFullName()
            + "\nAccount Type: " + account.getAccountType()
            + "\nAccount Balance: $" + account.getBalance());
}
                } catch (Exception e) {
                    messageBox("Error", "You must provide a valid customer number!").show();
                }
            }
        });
    }
}
