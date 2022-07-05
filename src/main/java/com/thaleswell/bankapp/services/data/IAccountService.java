package com.thaleswell.bankapp.services.data;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.exceptions.UnknownAccountTypeException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.User;

public interface IAccountService {
    
    List<Account> findUserBankAccounts(int userId);

    Account createBankAccount(User user, String type) throws UnknownAccountTypeException;
    
    double getAccountBalance(Account account);
}
