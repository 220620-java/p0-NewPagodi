package com.thaleswell.bankapp.models;

import java.util.Date;

/**
 * Models a monetary transaction for a bank account from the database
 * 
 * @author michael
 *
 */
public class Transaction {
    private int id;
    private int accountId;
    private Date datetime;
    private double amount;
    private double balance;
    
    public Transaction(int id, int accountId, Date datetime, double amount) {
        this.id = id;
        this.accountId = accountId;
        this.datetime = datetime;
        this.amount = amount; 
    }

    /** 
     * Gets the id assigned to this transaction by the database.
     * @return The transaction's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the id of the bank account that this transaction belongs to.
     * @return The transaction's bank account id.
     */
    public int getAccountId() {
        return accountId;
    }
    
    /**
     * Get the date and time at which this transaction occurred.
     * 
     * @return The java Date object for this transaction.
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * Gets the monetary amount of this transaction.
     * 
     * @return The transaction's amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Get the accoount's balance after this transaction is complete.
     * 
     * @return The account's balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Set the bank account balance for this transaction.
     * 
     * @param balance The account's balance after this transaction.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
