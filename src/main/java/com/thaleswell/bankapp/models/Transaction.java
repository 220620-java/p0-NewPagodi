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

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
