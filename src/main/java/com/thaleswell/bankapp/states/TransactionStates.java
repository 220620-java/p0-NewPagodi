package com.thaleswell.bankapp.states;

import java.util.Date;

import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.Transaction;
import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.IState;

class TransactionInfoState extends BankAppState {
    
    private Account account;
    private Transaction transaction;
    private double newBalance;
    
    TransactionInfoState(IIO io, Account account, Transaction transaction) {
        super(io);
        
        this.account = account;
        this.transaction = transaction;
        newBalance = 0;
    }
    
    @Override
    public void prepare() {
        newBalance = BankAppState.getDataServiceBundle()
                                 .getAccountService()
                                 .getAccountBalance(account);
    }

    @Override
    public String getMenu() {
        return
        "===Transaction Information===\n" +
        "\n" +
        "Account number: " + account.getId() + "\n" +
        "Account type: " + account.getType() + "\n" +
        "\n" +
        "Transaction id: " + transaction.getId() + "\n" +
        "Transaction amount: " + transaction.getAmount() + "\n" +
        "New balance: " + newBalance + "\n" +
        "\n";
    }

    @Override
    public String getPrompt() {
        return "Press enter to continue: ";
    }
    
    @Override
    public IState getNext() {
        return new AccountsMenuState(getIO());
    }
}

class DepositState extends BankAppState {
    
    private Account account;
    IState nextState;
    double balance;
    double deposit;
    boolean processDeposit;
    
    DepositState(IIO io, Account account) {
        super(io);
        this.account = account;
        nextState = this;
        balance = -1.0;
        deposit = -1.0;
        processDeposit = false;
    }
    
    @Override
    public void prepare() {
        balance = BankAppState.getDataServiceBundle()
                .getAccountService()
                .getAccountBalance(account);
    }
    
    @Override
    public String getMenu() {
        return
        "===Deposit to account " + account.getId() + " ===\n" +
        "\n" +
        "Current balance: " + balance + "\n" +
        "\n";
    }

    @Override
    public String getPrompt() {
        return "Enter the deposit amount or \"c\" to cancel: ";
    }

    @Override
    public void processInput(String input) {
        boolean inputIsDouble = false;
        
        try {
            deposit = Double.parseDouble(input);
            inputIsDouble = true;
        }
        catch (NumberFormatException e) {
        }
        
        if ( inputIsDouble ) {
            if ( deposit > 0 ) {
                processDeposit = true;
            }
            else {
                getIO().sendLine("Invalid deposit amount, please try again.");
                nextState = this;
            }
        }
        else {
            switch (input) {
                
            case "c":
                nextState = new AccountsMenuState(getIO());
                break;
            default:
                getIO().sendLine("Invalid input. Please try again.");
                nextState = this;
                break;
            }
        }
    }

    @Override
    public void performStateTask() {
        if ( processDeposit ) {
            // Have the bank machinery receive and verify the deposit.
            if ( BankAppState.getBankService().acceptDeposit(deposit) ) {
                // If the deposit is acceptable, record it in the database.
                Date date = new Date(System.currentTimeMillis());
                Transaction transaction =
                        BankAppState.getDataServiceBundle()
                                    .getTransactionService()
                                    .deposit(account, date, deposit);
                
                nextState =
                        new TransactionInfoState(getIO(), account, transaction);
            }
            else {
                // Otherwise return to the account menu.
                getIO().sendLine("The deposit has not been accepted.");
                nextState = new AccountsMenuState(getIO());
            }
        }
    }

    @Override
    public IState getNext() {
        return nextState;
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