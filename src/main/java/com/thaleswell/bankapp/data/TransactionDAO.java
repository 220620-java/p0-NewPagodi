package com.thaleswell.bankapp.data;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.models.Transaction;

/** 
 * The transaction DAO used to allow the database to receive, return, and otherwise
 * interact with transaction objects.
 * 
 * @author michael
 */
public interface TransactionDAO  extends DataAccessObject<Transaction> {
    
    /**
     * Get a list of all of the transactions for a given account.
     * @param accountId The id of the account whose transactions are needed.
     * @return The list of the account's transactions.
     */
    List<Transaction> findAllByAccountId(int accountId);
}
