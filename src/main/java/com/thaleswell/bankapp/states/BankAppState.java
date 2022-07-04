package com.thaleswell.bankapp.states;

import com.thaleswell.bankapp.DataServiceBundle;
import com.thaleswell.bankapp.models.User;
import com.thaleswell.bankapp.services.banking.IBankService;
import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.BaseState;

abstract public class BankAppState  extends BaseState{
    static private IBankService bankService;
    static private DataServiceBundle dataServiceBundle;
    static private User user;

    public BankAppState(IIO io) {
        super(io);
    }
    
    public static IBankService getBankService() {
        return bankService;
    }

    public static void setBankService(IBankService bankService) {
        BankAppState.bankService = bankService;
    }
    
    public static DataServiceBundle getDataServiceBundle() {
        return dataServiceBundle;
    }

    public static void setDataServiceBundle(DataServiceBundle dataServiceBundle) {
        BankAppState.dataServiceBundle = dataServiceBundle;
    }
    
    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        BankAppState.user = user;
    }
}
