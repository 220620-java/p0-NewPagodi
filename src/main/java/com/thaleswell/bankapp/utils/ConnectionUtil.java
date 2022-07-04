package com.thaleswell.bankapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//singleton design pattern: makes sure that you can only have one instance of something
//factory design pattern: generates and returns a particular object
public class ConnectionUtil {
    // singleton: private constructor, public static synchronized getter method
    private static ConnectionUtil connUtil;

    private ConnectionUtil() {
    }

    public static synchronized ConnectionUtil getConnectionUtil() {
        if (connUtil == null) {
            connUtil = new ConnectionUtil();
        }
        return connUtil;
    }

    // factory: creates Connection objects and returns them
    public Connection getConnection() {
        // when connecting to the DB, we need:
        // JDBC driver
        // database URL
        // username
        // password
        Connection conn = null;

        // using environment variables
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");

        if ( dbPass == null )
        {
          dbPass = "";
        }

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    dbUrl, dbUser, dbPass);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}