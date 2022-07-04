package com.thaleswell.bankapp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        User user = null;
        
        try (Connection conn = connUtil.getConnection()) {
            String sql = "select\r\n"
                    + "    user_id,\r\n"
                    + "    passwd \r\n"
                    + "from \r\n"
                    + "    bank_user\r\n"
                    + "where\r\n"
                    + "    username=?;";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);

            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String password = resultSet.getString("passwd");

                user = new User(username, password);
                user.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
}
