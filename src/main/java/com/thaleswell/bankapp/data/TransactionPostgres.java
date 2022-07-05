package com.thaleswell.bankapp.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thaleswell.bankapp.ds.ArrayList;
import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.models.Account;
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
    
    private java.sql.Timestamp Timestamp(java.util.Date date) {
        return new java.sql.Timestamp(date.getTime());
    }
    
    private java.util.Date Timestamp2Date(java.sql.Timestamp timestamp ) {
        return new java.util.Date(timestamp.getTime());
    }

    @Override
    public List<Transaction> findAllByAccountId(int accountId) {
        List<Transaction> transactions = new ArrayList<>();

        try (Connection conn = connUtil.getConnection()) {
            String sql = "select\r\n"
                    + "    transaction_id,\r\n"
                    + "    transaction_datetime,\r\n"
                    + "    amount, \r\n"
                    + "    sum(amount) over (order by transaction_datetime) as balance\r\n"
                    + "from\r\n"
                    + "    account_transaction\r\n"
                    + "where\r\n"
                    + "    account_id = ?\r\n"
                    + "order by\r\n"
                    + "    transaction_datetime;\r\n"
                    + "";

            // set up that statement with the database
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountId);

            // execute the statement
            ResultSet resultSet = stmt.executeQuery();

            // process the result set
            while (resultSet.next()) {
                int transactionId = resultSet.getInt("transaction_id");
                java.sql.Timestamp timestamp =
                        resultSet.getTimestamp("transaction_datetime");
                double amount = resultSet.getDouble("amount");
                double balance = resultSet.getDouble("balance");
                
                Transaction transaction =
                        new Transaction(transactionId,
                                        accountId, 
                                        Timestamp2Date(timestamp),
                                        amount);
                transaction.setBalance(balance);

                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
