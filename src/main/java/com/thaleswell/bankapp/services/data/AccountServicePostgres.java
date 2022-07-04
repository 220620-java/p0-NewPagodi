package com.thaleswell.bankapp.services.data;

import com.thaleswell.bankapp.data.AccountDAO;
import com.thaleswell.bankapp.data.AccountPostgres;
import com.thaleswell.bankapp.data.UserDAO;
import com.thaleswell.bankapp.data.UserPostgres;
import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.exceptions.UnknownAccountTypeException;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.models.User;

public class AccountServicePostgres implements IAccountService{
    private UserDAO userDao;
    private AccountDAO accountDAO;

    public AccountServicePostgres() {
        userDao = new UserPostgres();
        accountDAO = new AccountPostgres();
    }

    @Override
    public List<Account> findUserBankAccounts(int userId) {
        return accountDAO.findAllByUserId(userId);
    }

    @Override
    public Account createBankAccount(User user, String type) throws UnknownAccountTypeException {
        int typeId = accountDAO.getAccountTypeId(type);
        
        if ( typeId == -1 ) {
            throw new UnknownAccountTypeException();
        }
        
        Account account = new Account(-1, type, user.getId());

        return accountDAO.create(account);
    }

}
