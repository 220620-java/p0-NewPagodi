package com.thaleswell.bankapp.data;

import com.thaleswell.bankapp.models.User;

/** 
 * The User DAO used to allow the database to receive, return, and otherwise
 * interact with user objects.
 * 
 * @author michael
 */
public interface UserDAO extends DataAccessObject<User> {
    
    /**
     * Find a User object given the user's username.
     * 
     * @param username the which a User object is needed.
     * @return The User object corresponding to the given username or null if
     *         there is no user with that name.
     */
    public User findByUsername(String username);
}

