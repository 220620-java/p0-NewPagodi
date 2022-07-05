package com.thaleswell.bankapp;

import com.thaleswell.bankapp.services.data.AccountServicePostgres;
import com.thaleswell.bankapp.services.data.IAccountService;
import com.thaleswell.bankapp.services.data.ITransactionService;
import com.thaleswell.bankapp.services.data.IUserService;
import com.thaleswell.bankapp.services.data.TransactionServicePostgres;
import com.thaleswell.bankapp.services.data.UserServicePostgres;

public class DataServiceBundle {
    IUserService userService;
    IAccountService accountService;
    ITransactionService transactionService;
    
    public DataServiceBundle(){
        userService = new UserServicePostgres();
        accountService = new AccountServicePostgres();
        transactionService = new TransactionServicePostgres();
    }
    
    public IUserService getUserService() {
        return userService;
    }
    
    public IAccountService getAccountService() {
        return accountService;
    }
    
    public ITransactionService getTransactionService( ) {
        return transactionService;
    }
}
