package com.thaleswell.bankapp.services.data;

import java.util.Date;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.exceptions.TransactionOverdraftException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.Transaction;

public interface ITransactionService {

    Transaction deposit(Account account, Date date, double amount);
    
    Transaction withdraw(Account account, Date date, double amount)
            throws TransactionOverdraftException;
    
    List<Transaction> findAllByAccountId(int accountId);
}
