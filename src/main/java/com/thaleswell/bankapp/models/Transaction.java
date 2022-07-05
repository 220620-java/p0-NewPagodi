package com.thaleswell.bankapp.models;

import java.util.Date;

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

    public int getId() {
        return id;
    }

    public int getAccountId() {
        return accountId;
    }
    
    public Date getDatetime() {
        return datetime;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
