package com.thaleswell.bankapp.services.data;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.models.Account;

public interface IAccountService {
    
    List<Account> findUserBankAccounts(int userId);
}
