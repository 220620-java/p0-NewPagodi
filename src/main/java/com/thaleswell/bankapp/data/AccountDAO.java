package com.thaleswell.bankapp.data;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.exceptions.UnknownAccountTypeException;
import com.thaleswell.bankapp.models.Account;

/** 
 * The account DAO used to allow the database to receive, return, and otherwise
 * interact with account objects.
 * 
 * @author michael
 *
 */
public interface AccountDAO extends DataAccessObject<Account>{
    
    /**
     * Get a list of all bank accounts for a given user's id.
     * 
     * @param userId The id of the user whose accounts are needed.
     * @return The list of the user's accounts.
     */
    public List<Account> findAllByUserId(int userId);
    
    /**
     * Find the integer type id for a given type string.
     * 
     * @param type The string description of the account type.
     * @return the integer type id corresponding to type or -1 if there is
     *         no corresponding type.
     */
    public int getAccountTypeId(String type);
    
    /**
     * Get the current balance for a given bank account.
     * 
     * @param account The account for which the balance is needed.
     * @return The account's balance
     */
    public double getAccountBalance(Account account);
}
