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
    public Account create(Account account) {
        // We need to do 3 things:
        // 1) get the type id for account.type.
        // 2) add an entry to the account table.
        // 3) add an entry to the account_user table.
        
        // 1) get the type id for account.type.
        int typeId = getAccountTypeId(account.getType());
        Account completeAccount = null;
        
        try (Connection conn = connUtil.getConnection()) {
            int accountId;
            conn.setAutoCommit(false);
            
            // 2) add an entry to the account table.
            String sql = "insert into "+
                         "account(account_id,type_id) "+
                         "values "+
                         "(default ,?);";
            String[] keys = {"account_id"};
            
            PreparedStatement stmt = conn.prepareStatement(sql, keys);
            stmt.setInt(1, typeId);

            int rowsAffected = stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next() && rowsAffected==1) {
                accountId = resultSet.getInt("account_id");
                completeAccount = new Account(accountId,
                                              account.getType(),
                                              account.getUserId());
            } else {
                conn.rollback();
                return null;
            }
            
            // 3) add an entry to the account_user table.
            sql = "insert into\r\n"
                + "account_user(account_id, user_id)\r\n"
                + "values\r\n"
                + "(?,?);";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountId);
            stmt.setInt(2, account.getUserId());
            
            rowsAffected = stmt.executeUpdate();
            if ( rowsAffected !=1 ) {
                conn.rollback();
                return null;
            }
            else {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return completeAccount;
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
    
    @Override
    public int getAccountTypeId(String type) {
        int typeId = -1;
        
        try (Connection conn = connUtil.getConnection()) {
            String sql = "select type_id from account_type "
                       + "where description=?;";

            // set up that statement with the database
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);

            // execute the statement
            ResultSet resultSet = stmt.executeQuery();

            // process the result set
            if (resultSet.next()) {
                typeId = resultSet.getInt("type_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return typeId;
    }
    
}
