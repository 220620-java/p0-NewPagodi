package com.thaleswell.bankapp.models;

import java.util.Objects;

/**
 * Models a user of this banking application.
 *
 */
public class User {
    private int id;
    private String username;
    private String password;
    
    public User() {
        super();
        this.id = 0;
        this.username = "";
        this.password = "";
    }
    
    public User(String username, String password) {
        super();
        this.id = 0;
        this.username = username;
        this.password = password;
    }

    /**
     * Get's the id assigned to this user by the database.
     * 
     * @return The user's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id for this user object.
     * 
     * @param id The user's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get's the username for this user.
     * 
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for this object.
     * 
     * @param username The user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password for this user object.
     * 
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for this object.
     * 
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return id == other.id && Objects.equals(password, other.password)
                && Objects.equals(username, other.username);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
    }
}
