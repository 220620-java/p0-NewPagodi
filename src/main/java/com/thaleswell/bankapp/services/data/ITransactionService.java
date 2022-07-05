package com.thaleswell.bankapp.services.data;

import com.thaleswell.bankapp.data.TransactionDAO;
import com.thaleswell.bankapp.exceptions.TransactionOverdraftException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.Transaction;

public interface ITransactionService {

    Transaction deposit(Account account, double amount);
    
    Transaction withdraw(Account account, double amount) throws TransactionOverdraftException;
}
