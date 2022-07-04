package com.thaleswell.bankapp;

import com.thaleswell.bankapp.services.data.IUserService;
import com.thaleswell.bankapp.services.data.UserServicePostgres;

public class DataServiceBundle {
    IUserService userService;
    
    public DataServiceBundle(){
        userService = new UserServicePostgres();
    }
    
    public IUserService getUserService() {
        return userService;
    }
}
