package com.thaleswell.bankapp;

import com.thaleswell.bankapp.services.data.AccountServicePostgres;
import com.thaleswell.bankapp.services.data.IAccountService;
import com.thaleswell.bankapp.services.data.ITransactionService;
import com.thaleswell.bankapp.services.data.IUserService;
import com.thaleswell.bankapp.services.data.TransactionServicePostgres;
import com.thaleswell.bankapp.services.data.UserServicePostgres;

/**
 * Bundles up all of the data services into a single class.
 * 
 * @author michael
 */
public class DataServiceBundle {
    IUserService userService;
    IAccountService accountService;
    ITransactionService transactionService;
    
    public DataServiceBundle(){
        userService = new UserServicePostgres();
        accountService = new AccountServicePostgres();
        transactionService = new TransactionServicePostgres();
    }
    
    /**
     * Gets the user data service object.
     * 
     * @return The user service object.
     */
    public IUserService getUserService() {
        return userService;
    }
    
    /**
     * Gets the bank account service object.
     * 
     * @return The account service object. 
     */
    public IAccountService getAccountService() {
        return accountService;
    }
    
    /**
     * Gets the banking transaction data service object.
     * 
     * @return The transaction service object.
     */
    public ITransactionService getTransactionService( ) {
        return transactionService;
    }
}
