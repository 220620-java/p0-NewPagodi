package com.thaleswell.bankapp.data;

import com.thaleswell.bankapp.models.User;

public interface UserDAO {
    public User findByUsername(String username);
}

