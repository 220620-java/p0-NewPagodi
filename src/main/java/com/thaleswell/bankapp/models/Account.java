package com.thaleswell.bankapp.models;

public class Account {
    private int id;
    private String type;
    private String username;
    
    public Account(int id, String type, String username) {
        this.id = id;
        this.type = type;
        this.username = username;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
}
