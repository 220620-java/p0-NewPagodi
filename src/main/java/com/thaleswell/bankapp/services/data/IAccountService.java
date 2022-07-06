package com.thaleswell.bankapp.services.data;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.exceptions.UnknownAccountTypeException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.User;

/**
 * The interface for account services. This interface provides the application
 * with the methods it needs to store and retrieve information from the database
 * relating to user accounts.
 * 
 * @author michael
 */
public interface IAccountService {
    
    /**
     * Gets a list of all accounts owned by a user.
     * 
     * @param userId The id of the user for whom the list is requested.
     * @return The list of the user's accounts.
     */
    List<Account> findUserBankAccounts(int userId);

    /**
     * Create's a new bank account in the database and return an Account object
     * for that accoount.
     * 
     * @param user The user that will own the new account.
     * @param type The type of the new account (checking, savings, etc).
     * @return An account object for the newly created account.
     * @throws UnknownAccountTypeException Thrown it the type parameter does not
     *         match any of the types in the database.
     */
    Account createBankAccount(User user, String type) throws UnknownAccountTypeException;
    
    /**
     * Returns the current balance for this account.
     * 
     * @param account The account for which the balance is requested.
     * @return The account's balance.
     */
    double getAccountBalance(Account account);
}
