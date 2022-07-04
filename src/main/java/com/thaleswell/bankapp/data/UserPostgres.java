package com.thaleswell.bankapp.data;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.models.User;
import com.thaleswell.bankapp.utils.ConnectionUtil;

public class UserPostgres implements UserDAO {
    
    private ConnectionUtil connUtil;
    
    public UserPostgres() {
        connUtil = ConnectionUtil.getConnectionUtil();
    }

    @Override
    public User create(User t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(User t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(User t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public User findByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }
}
