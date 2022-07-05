package com.thaleswell.bankapp.services.data;

import java.util.Date;

import com.thaleswell.bankapp.data.TransactionDAO;
import com.thaleswell.bankapp.data.TransactionPostgres;
import com.thaleswell.bankapp.exceptions.TransactionOverdraftException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.Transaction;

public class TransactionServicePostgres implements ITransactionService{

    private TransactionDAO transactionDAO;
    
    public TransactionServicePostgres() {
        transactionDAO = new TransactionPostgres();
    }

    @Override
    public Transaction deposit(Account account, Date date, double amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Transaction withdraw(Account account, Date date, double amount)
            throws TransactionOverdraftException {
        // TODO Auto-generated method stub
        return null;
    }
}
