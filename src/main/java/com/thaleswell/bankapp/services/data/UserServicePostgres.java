package com.thaleswell.bankapp.services.data;

import com.thaleswell.bankapp.data.UserDAO;
import com.thaleswell.bankapp.data.UserPostgres;
import com.thaleswell.bankapp.exceptions.UsernameAlreadyExistsException;
import com.thaleswell.bankapp.models.User;

public class UserServicePostgres implements IUserService {
    private UserDAO userDao;

    public UserServicePostgres() {
        userDao = new UserPostgres();
    }

    @Override
    public User registerUser(String username, String password) throws UsernameAlreadyExistsException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User logIn(String username, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);

        if (user != null) {
            user.setPassword("");
        }
        return user;
    }

}
