package com.thaleswell.bankapp.states;

import com.thaleswell.bankapp.models.User;
import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.IState;

class ViewAccountsMenuState extends BankAppState {

    private IState nextState;
    
    ViewAccountsMenuState(IIO io) {
        super(io);
        nextState = new FinalState(getIO());
    }
    
    @Override
    public String getMenu() {
        // TODO Auto-generated method stub
        return "exiting";
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
        return nextState;
    }
}

class AccountsMenuState extends BankAppState {

    private IState nextState;
    
    AccountsMenuState(IIO io) {
        super(io);

        nextState = new StartState(getIO());
    }
    
    @Override
    public String getMenu() {
        User user = BankAppState.getUser();
        
        return
            "\n" +
            "===Welcome" +
            ( user== null ? "" : " " + user.getUsername() ) + "===\n" +
            "\n" +
            "1) View your bank accounts.\n" +
            "2) Create a new bank account.\n" +
            "q) Exit the system.\n" +
            "\n";
    }

    @Override
    public void processInput(String input) {
        boolean parseSucess = true;

        switch (input) {

        case "1":
            nextState = new ViewAccountsMenuState(getIO());
            break;
        case "2":
            getIO().send("Invalid input. Please try again.");
            nextState = this;
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
    public IState getNext() {
        return nextState;
    }
    
}
