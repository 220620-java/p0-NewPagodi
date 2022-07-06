package com.thaleswell.bankapp.states;

import java.util.Date;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.exceptions.TransactionOverdraftException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.Transaction;
import com.thaleswell.tui.io.IIO;
import com.thaleswell.tui.states.IState;

class TransactionHistory  extends BankAppState {
    private Account account;
    private String menu;

    TransactionHistory(IIO io, Account account) {
        super(io);

        this.account = account;
    }

    private String padTo(String s,int len) {
        int slen = s.length();
        
        StringBuilder b = new StringBuilder();
        
        for ( int i = 0 ; i < len-slen ; ++i ) {
            b.append(' ');
        }
        
        b.append(s);
        
        return b.toString();
    }
    
    @Override
    public void prepare() {
        List<Transaction> transactions =
                BankAppState.getDataServiceBundle()
                            .getTransactionService()
                            .findAllByAccountId(account.getId());
        
        StringBuilder builder = new StringBuilder();
        builder.append("===Transaction history for account " + account.getId() 
                      + "===\n");
        
        
        if ( transactions.size() == 0 ) {
            builder.append("\nThere are currently no transactions " + 
                           "for this account.\n");
        }
        else {
            builder.append("  Id                Date                Amount     Balance\n");
            builder.append("------  ----------------------------  ----------  ----------\n");
            
            for ( int i = 0 ; i < transactions.size(); ++i )
            {
                Transaction transaction = transactions.get(i);
                builder.append(padTo(String.valueOf(transaction.getId()),6));
                builder.append("  ");
                builder.append(transaction.getDatetime());
                builder.append("  ");
                String amountStr = to2DecimalPlaces(transaction.getAmount());
                builder.append(padTo(amountStr,10));
                builder.append("  ");
                String balanceStr = to2DecimalPlaces(transaction.getBalance());
                builder.append(padTo(balanceStr,10));
                builder.append("\n");
            }
            builder.append("\n");
        }

        menu = builder.toString();
    }

    @Override
    public String getMenu() {
        return menu;
    }

    @Override
    public String getPrompt() {
        return "Press enter to continue: ";
    }

    @Override
    public IState getNext() {
        return new AccountInfoState(getIO(), account);
    }
}

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
        newBalance = BankAppState.getDataServiceBundle().getAccountService().getAccountBalance(account);
    }

    @Override
    public String getMenu() {
        String descString;
        
        if ( transaction.getAmount() >= 0 ) {
            descString = "Deposit amount: $" + to2DecimalPlaces(transaction.getAmount());
        }
        else {
            // Report a positive number but note that it is a withdrawal.
            // Otherwise we must report something like:
            // Transaction amount: $-3.54
            // or
            // Transaction amount: -$3.54
            // and neither one looks right to me.
            descString = "Withdrawal amount: $" + to2DecimalPlaces(-transaction.getAmount());
        }
        
        return "===Transaction Information===\n" + 
                "\n" + "Account number: " + account.getId() + "\n" + 
                "Account type: " + account.getType() + "\n" + 
                "\n" + 
                "Transaction id: " + transaction.getId() + "\n"+ 
                descString + "\n" + 
                "\n" + 
                "New balance: $" + to2DecimalPlaces(newBalance) + "\n" + "\n";
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
        balance = BankAppState.getDataServiceBundle().getAccountService().getAccountBalance(account);
    }

    @Override
    public String getMenu() {
        String balanceString = to2DecimalPlaces(balance);
        
        return "===Deposit to account " + account.getId() + 
                " ===\n" + "\n" + "Current balance: $" + balanceString + "\n"
                + "\n";
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
        } catch (NumberFormatException e) {
        }

        if (inputIsDouble) {
            if (deposit > 0) {
                processDeposit = true;
            } else {
                getIO().sendLine("Invalid deposit amount, please try again.");
                nextState = this;
            }
        } else {
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
        if (processDeposit) {
            // Have the bank machinery receive and verify the deposit.
            if (BankAppState.getBankService().acceptDeposit(deposit)) {
                // If the deposit is acceptable, record it in the database.
                Date date = new Date(System.currentTimeMillis());
                Transaction transaction = BankAppState.getDataServiceBundle().getTransactionService().deposit(account,
                        date, deposit);

                nextState = new TransactionInfoState(getIO(), account, transaction);
            } else {
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
    IState nextState;
    double balance;
    double withdrawal;
    boolean processWithdrawal;

    WithdrawState(IIO io, Account account) {
        super(io);
        this.account = account;
        nextState = this;
        balance = -1.0;
        withdrawal = -1.0;
        processWithdrawal = false;
    }

    @Override
    public void prepare() {
        balance = BankAppState.getDataServiceBundle().getAccountService().getAccountBalance(account);
    }

    @Override
    public String getMenu() {
        return "===Withdraw from account " + account.getId() + " ===\n" + 
               "\n" + "Current balance: $" + to2DecimalPlaces(balance) + "\n"
                + "\n";
    }

    @Override
    public String getPrompt() {
        return "Enter the withdrawal amount or \"c\" to cancel: ";
    }

    @Override
    public void processInput(String input) {
        boolean inputIsDouble = false;

        try {
            withdrawal = Double.parseDouble(input);
            inputIsDouble = true;
        } catch (NumberFormatException e) {
        }

        if (inputIsDouble) {
            if (0 < withdrawal ) {
                processWithdrawal = true;
            } else {
                getIO().sendLine("Invalid deposit amount, please try again.");
                nextState = this;
            }
        } else {
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
        if ( processWithdrawal ) {
            try {

                Date date = new Date(System.currentTimeMillis());
                Transaction transaction =
                        BankAppState.getDataServiceBundle()
                                    .getTransactionService()
                                    .withdraw(account, date, withdrawal);
                
                nextState =
                        new TransactionInfoState(getIO(), account, transaction);
                
                // Finally have the machinery dispense the cash.
                BankAppState.getBankService().dispenseCash(withdrawal);
            }
            catch (TransactionOverdraftException e) {
                getIO().sendLine("This will overdraw the account. "
                                +"Please enter a new amount.");
                nextState = this;
            }
        }
    }

    @Override
    public IState getNext() {
        return nextState;
    }

}