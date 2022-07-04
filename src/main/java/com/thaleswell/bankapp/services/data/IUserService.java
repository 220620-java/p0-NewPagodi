package com.thaleswell.bankapp.services.data;

import com.thaleswell.bankapp.exceptions.UsernameAlreadyExistsException;
import com.thaleswell.bankapp.models.User;

public interface IUserService {
    /**
     * Creates a new user in the application and returns the newly 
     * created user. If a user with that username already exists, it 
     * will throw an exception.
     * 
     * @param user the new user to be created
     * @return the created user
     * @throws UsernameAlreadyExistsException
     */
    public User registerUser(String username, String password) throws UsernameAlreadyExistsException;
    
    /**
     * Retrieves a user with the specified username in the application, 
     * returning that user only if the specified password matches the password 
     * of the retrieved user.
     * 
     * @param username the username of the desired user
     * @param password the password of the desired user
     * @return the user matching the username if the password matches or null if there is 
     * no user with that username or the password does not match
     */
    public User logIn(String username, String password);
    
    /**
     * Find a user for a given username.
     * 
     * @param username  the username of the desired user
     * @return the user matching the username or null if there is no user with
     * that username.
     */
    User findByUsername(String username);
}
