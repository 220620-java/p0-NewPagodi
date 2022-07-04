package com.thaleswell.bankapp.states;

import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.IState;

class FinalState extends BankAppState {
    
    FinalState(IIO io) {
        super(io);
    }

    @Override
    public boolean isExitState() {
        return true;
    }

    @Override
    public IState getNext() {
        return this;
    }
    
    @Override
    public void teardown() {
        getIO().sendLine("bye.");
    }
}

public class StartState extends BankAppState {

    private IState nextState;
    public StartState(IIO io) {
        super(io);
        nextState = this;
    }

    @Override
    public String getMenu() {
        return
            "\n" +
            "===Main Menu===\n" +
            "\n" +
            "1) Login.\n" +
            "2) Create a new account.\n" +
            "q) Exit the system.\n" +
            "\n";
    }

    @Override
    public void processInput(String input) {
        boolean parseSucess = true;

        switch (input) {

        case "1":
            nextState = new GetUsernameState(getIO(),UserReason.LOGIN);
            break;
        case "2":
            nextState = new GetUsernameState(getIO(),UserReason.CREATE_ACCOUNT);
            break;
        case "q":
            nextState = new FinalState(getIO());
            break;
        default:
            parseSucess = false;
            break;
        }

        if ( !parseSucess ) {
            getIO().send("Invalid input. Please try again.");
        }
    }

    @Override
    public void performStateTask() {
    }

    @Override
    public IState getNext() {
        return nextState;
    }
}