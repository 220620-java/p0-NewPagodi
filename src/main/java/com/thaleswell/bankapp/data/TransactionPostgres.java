package com.thaleswell.bankapp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.models.Transaction;
import com.thaleswell.bankapp.utils.ConnectionUtil;

public class TransactionPostgres implements TransactionDAO {

    private ConnectionUtil connUtil;
    
    public TransactionPostgres() {
        connUtil = ConnectionUtil.getConnectionUtil();
    }

    @Override
    public Transaction create(Transaction transaction) {
        Transaction report = null;
        
        try (Connection conn = connUtil.getConnection()) {
            conn.setAutoCommit(false);
            
            String sql = "insert into account_transaction\r\n"
                    + "(transaction_id, account_id, transaction_datetime, amount)\r\n"
                    + "values\r\n"
                    + "(default, ?, ?, ?);";
            String[] keys = {"transaction_id"};
            
            PreparedStatement stmt = conn.prepareStatement(sql, keys);
            stmt.setInt(1, transaction.getAccountId());
            stmt.setTimestamp(2, Timestamp(transaction.getDatetime()));
            stmt.setDouble(3, transaction.getAmount());
            
            int rowsAffected = stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            
            if (resultSet.next() && rowsAffected==1) {
                report = new Transaction(resultSet.getInt("transaction_id"),
                                         transaction.getAccountId(),
                                         transaction.getDatetime(),
                                         transaction.getAmount());
                conn.commit();
            } else {
                conn.rollback();
                return null;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return report;
    }

    @Override
    public Transaction findById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Transaction> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Transaction t) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Transaction t) {
        // TODO Auto-generated method stub
        
    }
    
    private java.sql.Timestamp Timestamp(java.util.Date date) {
        return new java.sql.Timestamp(date.getTime());
    }
}
