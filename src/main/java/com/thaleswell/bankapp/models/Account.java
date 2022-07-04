package com.thaleswell.bankapp.models;

public class Account {
    private int id;
    private String type;
    private int userId;
    
    public Account(int id, String type, int userId) {
        this.id = id;
        this.type = type;
        this.userId = userId;
    }
    
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

}
