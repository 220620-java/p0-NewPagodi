package com.thaleswell.bankapp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thaleswell.bankapp.ds.ArrayList;
import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.models.Account;
import com.thaleswell.bankapp.utils.ConnectionUtil;

public class AccountPostgres implements AccountDAO{

    private ConnectionUtil connUtil;
    
    public AccountPostgres() {
        connUtil = ConnectionUtil.getConnectionUtil();
    }

    @Override
    public Account create(Account t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Account findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Account> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Account t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Account t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Account> findAllByUserId(int userId) {
        List<Account> accounts = new ArrayList<>();

        try (Connection conn = connUtil.getConnection()) {
            String sql = "select\r\n"
                    + "    account.account_id,\r\n"
                    + "    description\r\n"
                    + "from\r\n"
                    + "    account\r\n"
                    + "    inner join account_type\r\n"
                    + "        on account.type_id = account_type.type_id\r\n"
                    + "    inner join account_user\r\n"
                    + "        on account_user.account_id = account.account_id\r\n"
                    + "where\r\n"
                    + "    user_id = ?;";

            // set up that statement with the database
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);

            // execute the statement
            ResultSet resultSet = stmt.executeQuery();

            // process the result set
            while (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                String description = resultSet.getString("description");

                accounts.add(new Account(accountId, description,userId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }
}
