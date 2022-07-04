package com.thaleswell.bankapp.data;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.models.Account;

public interface AccountDAO extends DataAccessObject<Account>{
    public List<Account> findAllByUserId(int userId);
}
