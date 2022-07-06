package com.thaleswell.bankapp.services.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thaleswell.bankapp.data.AccountDAO;
import com.thaleswell.bankapp.data.UserDAO;
import com.thaleswell.bankapp.ds.ArrayList;
import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.exceptions.UnknownAccountTypeException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.User;

@ExtendWith(MockitoExtension.class)
class IAccountServiceTest {
    
    @InjectMocks
    private IAccountService accountServ = new AccountServicePostgres();
    
    @Mock
    private UserDAO userDao;
    
    @Mock
    private AccountDAO accountDAO;

    @BeforeAll
    public static void setUp() {
        System.out.println("======BEGIN IAccountService Tests=======");
    }
    
    @Test
    void testFindBankAccounts() {
        Account mockAccount = new Account(1, "", 1);
        List<Account> mockList = new ArrayList<>();
        mockList.add(mockAccount);
        
        Mockito.when(accountDAO.findAllByUserId(anyInt())).thenReturn(mockList);
        List<Account> returnedList = accountServ.findUserBankAccounts(1);

        // assertion
        assertNotEquals(0,returnedList.size());
    }
    
    @Test
    void testFindBankAccountsFailure() {
        List<Account> mockList = new ArrayList<>();
        
        Mockito.when(accountDAO.findAllByUserId(anyInt())).thenReturn(mockList);
        List<Account> returnedList = accountServ.findUserBankAccounts(1);

        // assertion
        assertEquals(0,returnedList.size());
    }
    
    @Test
    void createNewBankAccount() {
        Account mockAccount = new Account(1, "", 1);
        User mockUser = new User("test","test");

        // Ensure that the DAO will always return 1 as the account type
        // indicating a valid account type.
        Mockito.when(accountDAO.getAccountTypeId(anyString())).thenReturn(1);
        Mockito.when(accountDAO.create(any(Account.class))).thenReturn(mockAccount);

        Account returnedAccount = null;
        try {
            returnedAccount = accountServ.createBankAccount(mockUser,"checking");
        }
        catch (UnknownAccountTypeException e) {
        }

        // assertion
        assertNotNull(returnedAccount);
    }

    @Test
    void createNewBankAccountFailure() {
        Account mockAccount = new Account(1, "", 1);
        User mockUser = new User("test","test");

        // Ensure that the DAO will always return -1 as the account type
        // indicating an invalid account type.
        Mockito.when(accountDAO.getAccountTypeId(anyString())).thenReturn(-1);

        Account returnedAccount = null;
        try {
            returnedAccount = accountServ.createBankAccount(mockUser,"checking");
        }
        catch (UnknownAccountTypeException e) {
        }

        // assertion
        assertNull(returnedAccount);
    }
    
    @Test
    void testGetAccountBalance() {
        Account mockAccount = new Account(1, "", 1);
        
        // Have the DAO always give a nonzero balance for all accounts.
        Mockito.when(accountDAO.getAccountBalance(any(Account.class))).thenReturn(1.0);

        double returnedBalance = accountServ.getAccountBalance(mockAccount);

        // assertion
        assertNotEquals(0.0, returnedBalance);
    }
}
