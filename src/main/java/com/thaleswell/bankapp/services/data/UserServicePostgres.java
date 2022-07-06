package com.thaleswell.bankapp.services.data;

import com.thaleswell.bankapp.data.UserDAO;
import com.thaleswell.bankapp.data.UserPostgres;
import com.thaleswell.bankapp.exceptions.UsernameAlreadyExistsException;
import com.thaleswell.bankapp.models.User;

/**
 * As implementation of IUserService using a PostgreSQL database.
 * 
 * @author michael
 */
public class UserServicePostgres implements IUserService {
    private UserDAO userDao;

    public UserServicePostgres() {
        userDao = new UserPostgres();
    }

    @Override
    public User registerUser(String username, String password) throws UsernameAlreadyExistsException {
        User user = userDao.findByUsername(username);

        if (user != null) {
            throw new UsernameAlreadyExistsException();
        }
        else {
            user = new User(username, password);
            user = userDao.create(user);
        }
        
        return user;
    }

    @Override
    public User logIn(String username, String password) {
        // Find the user by name.
        User user = findByUsername(username);
        
        // If the user is not null verify that passwords match.
        if ( user != null) {
            if ( !user.getPassword().equals(password) ) {
                user = null;
            }
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
