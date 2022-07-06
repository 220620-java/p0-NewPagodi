package com.thaleswell.bankapp.services.banking;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.thaleswell.tui.io.ConsoleIO;
import com.thaleswell.tui.io.IIO;

class IBankServiceTest {
    
    static Scanner scanner;
    static IIO consoleIO;
    static IBankService bankServ; 
    
    @BeforeAll
    public static void setUp() {
        System.out.println("======BEGIN IBankService Tests=======");
        scanner = new Scanner(System.in);
        consoleIO = new ConsoleIO(scanner);
        bankServ = new DummyBankService(consoleIO);
    }
    
    @Test
    void testDispenseCash() {
        assertDoesNotThrow(() -> bankServ.dispenseCash(1.00));
    }
    
    @Test
    void testAcceptDeposit() {
        assertTrue(bankServ.acceptDeposit(1));
    }

}
