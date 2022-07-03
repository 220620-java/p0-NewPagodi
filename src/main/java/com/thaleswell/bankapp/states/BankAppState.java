package com.thaleswell.bankapp.states;

import com.thaleswell.bankapp.services.banking.IBankService;
import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.BaseState;

abstract public class BankAppState  extends BaseState{
    static private IBankService bankService;

    public BankAppState(IIO io) {
        super(io);
    }
    
    public static IBankService getBankService() {
        return bankService;
    }

    public static void setBankService(IBankService bankService) {
        BankAppState.bankService = bankService;
    }
}
