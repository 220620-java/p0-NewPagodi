package com.thaleswell.bankapp;

import java.util.Scanner;

import com.thaleswell.bankapp.data.AccountPostgres;
import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.services.banking.DummyBankService;
import com.thaleswell.bankapp.states.BankAppState;
import com.thaleswell.bankapp.states.StartState;
import com.thaleswell.tui.TUI;
import com.thaleswell.tui.io.ConsoleIO;
import com.thaleswell.tui.io.IIO;

public class Main {
    public static void main(String args[]) {
        
        // Create a scanner and use it to create a consoleIO for out
        // TUI states.
        final Scanner scanner = new Scanner(System.in);
        IIO consoleIO = new ConsoleIO(scanner);

        // Create a DummyBankService for our TUI states.
        DummyBankService bankServices = new DummyBankService(consoleIO);
        DataServiceBundle dataServiceBundle = new DataServiceBundle();
        BankAppState.setBankService(bankServices);
        BankAppState.setDataServiceBundle(dataServiceBundle);

        // Create a new start state and run the TUI loop.
        StartState start = new StartState(consoleIO);
        TUI ui = new TUI(consoleIO, start);
        ui.runLoop();

        // Once the loop is complete, close the scanner and exit the app.
        scanner.close();
    }
}
