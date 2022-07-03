package com.thaleswell.bankapp;

import com.thaleswell.bankapp.services.data.DummyUserService;
import com.thaleswell.bankapp.services.data.IUserService;

public class DataServiceBundle {
    IUserService userService;
    
    public DataServiceBundle(){
        userService = new DummyUserService();
    }
    
    public IUserService getUserService() {
        return userService;
    }
}
