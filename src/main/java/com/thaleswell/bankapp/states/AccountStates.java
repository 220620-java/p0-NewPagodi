package com.thaleswell.bankapp.states;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.User;
import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.IState;

class ViewAccountsMenuState extends BankAppState {

    private IState nextState;
    List<Account> accounts;
    
    ViewAccountsMenuState(IIO io) {
        super(io);
        nextState = new FinalState(getIO());
        accounts = null;
    }

    @Override
    public void prepare() {
        if ( accounts == null ) {
            User user = BankAppState.getUser();
            
            accounts = BankAppState.getDataServiceBundle().getAccountService()
                    .findUserBankAccounts(user.getId());
        }
    }

    @Override
    public String getMenu() {
        User user = BankAppState.getUser();
        StringBuilder builder = new StringBuilder();
        builder.append("===" + user.getUsername() + "'s bank accounts===\n");
        
        if ( accounts.size() == 0 ) {
            builder.append("There are currently no bank accounts\n");
        }
        else {
            for ( int i = 0 ; i < accounts.size() ; ++i ) {
                Account account = accounts.get(i);
                
                builder.append(i+1);
                builder.append(") " + account.getId() + " " + account.getType()
                    + "\n");
            }
        }
        
        builder.append("r) return to the previous menu.\n" +
                "q) Exit the system.\n" +
                "\n");

        return builder.toString();
    }

    @Override
    public void processInput(String input) {
        int accountListNumber = 0;
        boolean inputIsInt = false;
        
        nextState = new AccountsMenuState(getIO());
        
        try {
            accountListNumber = Integer.parseInt(input);
            inputIsInt = true;
        }
        catch (NumberFormatException e) {
        }

        if ( inputIsInt ) {
            // If the input is an integer, see if it is in the valid range.
            if ( 1 <= accountListNumber && accountListNumber <= accounts.size() ) {
                getIO().send("You selected " + accountListNumber
                        + ". Not Implemented yet.");
                nextState = new AccountsMenuState(getIO());
            }
            else {
                getIO().send("Invalid account choice, Please try again.");
                nextState = this;
            }
        }
        else {
            // The input was not an integer, handle it as usual.
            switch (input) {
                
            case "r":
                nextState = new AccountsMenuState(getIO());
                break;
            case "q":
                nextState = new FinalState(getIO());
                break;
            default:
                getIO().send("Invalid input. Please try again.");
                nextState = this;
                break;
            }
        }
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
