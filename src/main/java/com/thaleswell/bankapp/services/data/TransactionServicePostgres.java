package com.thaleswell.bankapp.services.data;

import java.util.Date;

import com.thaleswell.bankapp.data.AccountDAO;
import com.thaleswell.bankapp.data.AccountPostgres;
import com.thaleswell.bankapp.data.TransactionDAO;
import com.thaleswell.bankapp.data.TransactionPostgres;
import com.thaleswell.bankapp.exceptions.TransactionOverdraftException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.Transaction;

public class TransactionServicePostgres implements ITransactionService{

    private TransactionDAO transactionDAO;
    private AccountDAO accountDAO;
    
    public TransactionServicePostgres() {
        transactionDAO = new TransactionPostgres();
        accountDAO = new AccountPostgres();
    }

    @Override
    public Transaction deposit(Account account, Date date, double amount) {
        if ( amount <= 0 ) {
            return null;
        }
        else {
            Transaction transaction =
                    new Transaction(-1, account.getId(), date, amount);
            
            return transactionDAO.create(transaction);
        }
    }

    @Override
    public Transaction withdraw(Account account, Date date, double amount)
            throws TransactionOverdraftException {
        
        if ( amount <= 0 ) {
            return null;
        }
        else {
            double balance = accountDAO.getAccountBalance(account);
            if ( amount > balance ) {
                throw new TransactionOverdraftException();
            }
            else {
                // To make a withdrawal, create a transaction with the negative
                // of the amount.
                Transaction transaction =
                        new Transaction(-1, account.getId(), date, -amount);
                
                return transactionDAO.create(transaction);
            }
        }
    }
}
