package com.thaleswell.bankapp.states;

import com.thaleswell.bankapp.models.Account;
import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.IState;

class DepositState extends BankAppState {
    
    private Account account;
    
    DepositState(IIO io, Account account) {
        super(io);
        this.account = account;
    }
    
    @Override
    public String getMenu() {
        // TODO Auto-generated method stub
        return super.getMenu();
    }

    @Override
    public String getPrompt() {
        // TODO Auto-generated method stub
        return super.getPrompt();
    }

    @Override
    public void processInput(String input) {
        // TODO Auto-generated method stub
        super.processInput(input);
    }

    @Override
    public void performStateTask() {
        // TODO Auto-generated method stub
        super.performStateTask();
    }

    @Override
    public IState getNext() {
        // TODO Auto-generated method stub
        return null;
    }
    
}

class WithdrawState extends BankAppState {
    private Account account;
    
    WithdrawState(IIO io, Account account) {
        super(io);
        this.account = account;
    }
    
    @Override
    public String getMenu() {
        // TODO Auto-generated method stub
        return super.getMenu();
    }

    @Override
    public String getPrompt() {
        // TODO Auto-generated method stub
        return super.getPrompt();
    }

    @Override
    public void processInput(String input) {
        // TODO Auto-generated method stub
        super.processInput(input);
    }

    @Override
    public void performStateTask() {
        // TODO Auto-generated method stub
        super.performStateTask();
    }

    @Override
    public IState getNext() {
        // TODO Auto-generated method stub
        return null;
    }
}