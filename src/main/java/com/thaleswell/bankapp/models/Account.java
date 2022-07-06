package com.thaleswell.bankapp.models;

/**
 * Models an bank account from the database
 * 
 * @author michael
 *
 */
public class Account {
    private int id;
    private String type;
    private int userId;
    
    public Account(int id, String type, int userId) {
        this.id = id;
        this.type = type;
        this.userId = userId;
    }
    
    /**
     * Gets the account id for this object.
     * 
     * @return The objects account id.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the string description of the type of this account (checking,
     * savings, etc).
     * 
     * @return A string containing the account description.
     */
    public String getType() {
        return type;
    }
    
    /**
     * Gets the id of the user that owns this bank account.
     * 
     * @return The id of the account user.
     */
    public int getUserId() {
        return userId;
    }

}
