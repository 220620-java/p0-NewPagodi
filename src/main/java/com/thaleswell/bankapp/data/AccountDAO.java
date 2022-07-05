package com.thaleswell.bankapp.data;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.exceptions.UnknownAccountTypeException;
import com.thaleswell.bankapp.models.Account;

public interface AccountDAO extends DataAccessObject<Account>{
    public List<Account> findAllByUserId(int userId);
    
    /**
     * Find the integer type id for a given type string.
     * 
     * @param type The string description of the account type.
     * @return the integer type id corresponding to type or -1 if there is
     *         no corresponding type.
     */
    public int getAccountTypeId(String type);
    
    public double getAccountBalance(Account account);
}
