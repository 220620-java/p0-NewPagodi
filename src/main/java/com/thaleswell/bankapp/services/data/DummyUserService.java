package com.thaleswell.bankapp.services.data;

import com.thaleswell.bankapp.exceptions.UsernameAlreadyExistsException;
import com.thaleswell.bankapp.models.User;

public class DummyUserService implements IUserService{

    @Override
    public User registerUser(String username, String password) throws UsernameAlreadyExistsException {
        
        if ( username.equals("exists") ) {
            throw new UsernameAlreadyExistsException();
        }
        
        User user = new User(username, password);
        user.setId(2);
        
        return user;
    }

    @Override
    public User logIn(String username, String password) {
        if ( username.equals("dne") || password.equals("invalid") ) {
            return null;
        }
        else {
            User user = new User(username, password);
            user.setId(2);
            
            return user;
        }

    }

}
