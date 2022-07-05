package com.thaleswell.bankapp.data;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.models.Transaction;
import com.thaleswell.bankapp.utils.ConnectionUtil;

public class TransactionPostgres implements TransactionDAO {

    private ConnectionUtil connUtil;
    
    public TransactionPostgres() {
        connUtil = ConnectionUtil.getConnectionUtil();
    }

    @Override
    public Transaction create(Transaction t) {
        // TODO Auto-generated method stub
        return null;
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
}
