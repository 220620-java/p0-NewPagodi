package com.thaleswell.bankapp.services.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thaleswell.bankapp.data.AccountDAO;
import com.thaleswell.bankapp.data.TransactionDAO;
import com.thaleswell.bankapp.ds.ArrayList;
import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.exceptions.TransactionOverdraftException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.Transaction;


@ExtendWith(MockitoExtension.class)
class ITransactionServiceTest {

    @InjectMocks
    private ITransactionService transactionServ = new TransactionServicePostgres();

    @Mock
    private TransactionDAO transactionDao;

    @Mock
    private AccountDAO accountDao;

    @BeforeAll
    public static void setUp() {
        System.out.println("======BEGIN ITransactionService Tests=======");
    }

    @Test
    void testNegativeDeposit() {
        Date now = new Date(System.currentTimeMillis());
        Account mockAccount = new Account(1, "", 1);
        Transaction returnedTransaction
            = transactionServ.deposit(mockAccount, now, -1.0);

        // assertion
        assertNull(returnedTransaction);
    }
    
    @Test
    void testValidDeposit() {
        Date now = new Date(System.currentTimeMillis());

        Transaction mockTransaction = new Transaction(1, 1, now, 3);

        // Ensure that the DAO will tell us the username is available
        // and give back a non null object.
        Mockito.when(transactionDao.create(any(Transaction.class))).thenReturn(mockTransaction);

        Account account = new Account(1, "", 1);
        Transaction returnedTransaction = transactionServ.deposit(account, now, 1.0);

        // assertion
        assertNotNull(returnedTransaction);
    }

    @Test
    void testInvalidDeposit() {
        Date now = new Date(System.currentTimeMillis());

        Account account = new Account(1, "", 1);
        Transaction returnedTransaction = transactionServ.deposit(account, now, 1.0);

        // assertion
        assertNull(returnedTransaction);
    }

    @Test
    void testNegativeWithdrawal() {
        Date now = new Date(System.currentTimeMillis());
        Account mockAccount = new Account(1, "", 1);
        Transaction mockTransaction = new Transaction(1, 1, now, 3);
        
        Transaction returnedTransaction = mockTransaction;
        
        try {
            returnedTransaction
                = transactionServ.withdraw(mockAccount, now, -1.0);
        }
        catch(TransactionOverdraftException e) {
            
        }

        // assertion
        assertNull(returnedTransaction);
    }
    
    @Test
    void testValidWithdrawal() {
        Date now = new Date(System.currentTimeMillis());
        double withdrawalAmount = 2.0;
        Account mockAccount = new Account(1, "", 1);
        Transaction mockTransaction = new Transaction(1, 1, now, 3);

        // Ensure that the DAO will tell us the balance available is larger
        // than the withdrawal.
        Mockito.when(accountDao.getAccountBalance(mockAccount)).thenReturn(withdrawalAmount + 1.0);
        Mockito.when(transactionDao.create(any(Transaction.class))).thenReturn(mockTransaction);

        Transaction returnedTransaction = null;

        try {
            returnedTransaction = transactionServ.withdraw(mockAccount, now, withdrawalAmount);
        } catch (TransactionOverdraftException e) {
        }

        // assertion
        assertNotNull(returnedTransaction);
    }

    @Test
    void testInvalidWithdrawal() {
        Date now = new Date(System.currentTimeMillis());
        double withdrawalAmount = 2.0;
        Account mockAccount = new Account(1, "", 1);

        // Ensure that the DAO will tell us the balance available is less
        // than the withdrawal.
        Mockito.when(accountDao.getAccountBalance(any(Account.class))).thenReturn(withdrawalAmount - 1.0);

        // when asserting an exception was thrown, the structure is a little
        // different - you set up a lambda function that JUnit will call and
        // check for an exception
        assertThrows(TransactionOverdraftException.class, () -> {
            transactionServ.withdraw(mockAccount, now, withdrawalAmount);
        });
    }

    @Test
    void testValidTransactionList() {
        Date now = new Date(System.currentTimeMillis());
        Transaction mockTransaction = new Transaction(1, 1, now, 3);
        ArrayList<Transaction> mockList = new ArrayList<>();
        mockList.add(mockTransaction);

        // Ensure that the DAO will tell us the balance available is less
        // than the withdrawal.
        Mockito.when(transactionDao.findAllByAccountId(anyInt())).thenReturn(mockList);

        List<Transaction> returnedList = transactionServ.findAllByAccountId(1);

        // when asserting an exception was thrown, the structure is a little
        // different - you set up a lambda function that JUnit will call and
        // check for an exception
        assertTrue(returnedList.size() > 0);
    }

    @Test
    void testEmptyTransactionList() {
        ArrayList<Transaction> mockList = new ArrayList<>();

        // Ensure that the DAO will always give and empty list.
        Mockito.when(transactionDao.findAllByAccountId(anyInt())).thenReturn(mockList);

        List<Transaction> returnedList = transactionServ.findAllByAccountId(1);

        assertEquals(0, returnedList.size());
    }

}
