package com.thaleswell.mb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.postgresql.Driver");
          c = DriverManager
             .getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=p0",
             "postgres", "");
            
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "select type_id,description from account_type;" );
          while ( rs.next() ) {
             int id = rs.getInt("type_id");
             String  name = rs.getString("description");
             System.out.println( "ID = " + id );
             System.out.println( "NAME = " + name );
             System.out.println();
          }
          rs.close();
          stmt.close();
          c.close();
          
            
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}
