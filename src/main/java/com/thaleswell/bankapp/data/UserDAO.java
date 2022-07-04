package com.thaleswell.bankapp.data;

import com.thaleswell.bankapp.models.User;

public interface UserDAO extends DataAccessObject<User> {
    public User findByUsername(String username);
}

