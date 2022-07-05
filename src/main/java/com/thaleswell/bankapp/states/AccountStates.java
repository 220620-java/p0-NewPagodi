package com.thaleswell.bankapp.states;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.exceptions.UnknownAccountTypeException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.User;
import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.IState;

class BankAccountCreatedState extends BankAppState {
    
    private Account account;
    
    BankAccountCreatedState(IIO io, Account account) {
        super(io);
        this.account = account;
    }

    @Override
    public String getPrompt() {
        return "A new " + account.getType() + " account has been created\n"
             + "with account number " + account.getId() + "\n"
             + "Press enter to return to the bank account menu.";
    }

    @Override
    public IState getNext() {
        // TODO Auto-generated method stub
        return new AccountsMenuState(getIO());
    }
}

class CreateBankAccountState extends BankAppState {
    
    IState nextState;
    int accountType;
    
    CreateBankAccountState(IIO io) {
        super(io);
        nextState = this;
        accountType = -1;
    }

    @Override
    public String getMenu() {
        return
                "\n" +
                "===Which type of bank account do you want to create===" +
                "\n" +
                "1) Checking.\n" +
                "2) Savings.\n" +
                "c) Cancel.\n" +
                "q) Exit the system.\n" +
                "\n";
    }

    @Override
    public void processInput(String input) {
        switch (input) {

        case "1":
            accountType = 1;
            break;
        case "2":
            accountType = 2;
            break;
        case "c":
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

    @Override
    public void performStateTask() {
        if ( accountType != -1 ) {
            String accountTypeStr = (accountType == 1 ? "checking" : "savings");
            User user = BankAppState.getUser();
            
            try {
                Account account = BankAppState.getDataServiceBundle()
                        .getAccountService()
                        .createBankAccount(user, accountTypeStr);
                nextState = new BankAccountCreatedState(getIO(), account);
            }
            catch(UnknownAccountTypeException e) {
                getIO().sendLine("Error while createing account.");
                nextState = new AccountsMenuState(getIO());
            }
        }
    }

    @Override
    public IState getNext() {
        return nextState;
    }
}

class ViewAccountsMenuState extends BankAppState {

    private IState nextState;
    List<Account> accounts;
    boolean returnVisit;
    
    ViewAccountsMenuState(IIO io) {
        super(io);
        nextState = new FinalState(getIO());
        accounts = null;
        returnVisit = false;
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
        // It's possible that we are returning to this state because of invalid
        // input. The menu should only be displayed the first time and only the
        // prompt should be displayed on subsequent visits.
        
        if ( returnVisit )
        {
            return "";
        }
        
        returnVisit = true;
        
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
                getIO().sendLine("You selected " + accountListNumber
                        + ". Not Implemented yet.");
                nextState = new AccountsMenuState(getIO());
            }
            else {
                getIO().sendLine("Invalid account choice, Please try again.");
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
                getIO().sendLine("Invalid input. Please try again.");
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
        nextState = this;
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

        switch (input) {

        case "1":
            nextState = new ViewAccountsMenuState(getIO());
            break;
        case "2":
            nextState = new CreateBankAccountState(getIO());
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

    @Override
    public IState getNext() {
        return nextState;
    }
    
}
