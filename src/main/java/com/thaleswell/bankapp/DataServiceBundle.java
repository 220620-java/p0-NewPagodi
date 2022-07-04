package com.thaleswell.bankapp;

import com.thaleswell.bankapp.services.data.AccountServicePostgres;
import com.thaleswell.bankapp.services.data.IAccountService;
import com.thaleswell.bankapp.services.data.IUserService;
import com.thaleswell.bankapp.services.data.UserServicePostgres;

public class DataServiceBundle {
    IUserService userService;
    IAccountService accountService;
    
    public DataServiceBundle(){
        userService = new UserServicePostgres();
        accountService = new AccountServicePostgres();
    }
    
    public IUserService getUserService() {
        return userService;
    }
    
    public IAccountService getAccountService() {
        return accountService;
    }
}
