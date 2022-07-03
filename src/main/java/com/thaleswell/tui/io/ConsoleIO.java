package com.thaleswell.tui.io;

import java.util.Scanner;

public class ConsoleIO implements IIO {
    
    private final Scanner scanner;
    
    public ConsoleIO(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void sendLine(String msg) {
        System.out.println(msg);
    }

    @Override
    public void send(String msg) {
        System.out.print(msg);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

}
