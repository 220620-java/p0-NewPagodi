package com.thaleswell.bankapp.services.data;

import java.util.Date;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.exceptions.TransactionOverdraftException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.Transaction;

/**
 * The interface for transaction services. This interface provides the application
 * with the methods it needs to store and retrieve information from the database
 * relating to bank account transactions.
 * 
 * @author michael
 */
public interface ITransactionService {
    
    /**
     * Records a deposit in the database and returns a Transaction for it.
     * 
     * @param account The account object receiving the deposit.
     * @param date The date and time of the deposit.
     * @param amount The monetary amount of the deposit.
     * @return A transaction object for this deposit.
     */
    Transaction deposit(Account account, Date date, double amount);
    
    /**
     * Records a withdrawal in the database and returns a Transaction for it.
     * 
     * @param account The account object receiving the deposit.
     * @param date The date and time of the deposit.
     * @param amount The monetary amount of the deposit.
     * @return A transaction object for this deposit.
     * @throws TransactionOverdraftException Thrown if the amount of the
     *         withdrawal is larger than the account's balance.
     */
    Transaction withdraw(Account account, Date date, double amount)
            throws TransactionOverdraftException;
    
    /**
     * Returns a list of all transactions for a given account.
     * 
     * @param accountId The id of the account for which the list is requested.
     * @return A list of the account's transactions.
     */
    List<Transaction> findAllByAccountId(int accountId);
}
