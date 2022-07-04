package com.thaleswell.bankapp.states;

import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.IState;

class AccountsMenuState extends BankAppState {

    AccountsMenuState(IIO io) {
        super(io);
    }
    
    @Override
    public String getMenu() {
        return
            "\n" +
            "===Accounts Menu===\n" +
            "\n" +
            "q) Exit the system.\n" +
            "\n";
    }

    @Override
    public void processInput(String input) {
    }

    @Override
    public IState getNext() {
        return new FinalState(getIO());
    }
    
}
