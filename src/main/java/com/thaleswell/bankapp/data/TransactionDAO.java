package com.thaleswell.bankapp.data;

import com.thaleswell.bankapp.ds.List;
import com.thaleswell.bankapp.models.Transaction;

public interface TransactionDAO  extends DataAccessObject<Transaction> {
    List<Transaction> findAllByAccountId(int accountId);
}
