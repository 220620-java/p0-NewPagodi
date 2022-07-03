package com.thaleswell.bankapp;

import java.util.Scanner;
import com.thaleswell.bankapp.states.StartState;
import com.thaleswell.tui.TUI;
import com.thaleswell.tui.io.ConsoleIO;
import com.thaleswell.tui.io.IIO;

public class Main {
    public static void main(String args[]) {
        final Scanner scanner = new Scanner(System.in);
        IIO consoleIO = new ConsoleIO(scanner);

        StartState start = new StartState(consoleIO);

        TUI ui = new TUI(consoleIO, start);
        ui.runLoop();

        scanner.close();
    }
}
