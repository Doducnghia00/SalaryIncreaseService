package com.tutorial.Springboot.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    public static Connection con;
    private final String dbClass = "com.mysql.cj.jdbc.Driver";
    static final String urlString = "jdbc:mysql://localhost/test";
    static final String rootString = "root";
    static final String passString = "123456";

    public DAO() {
        if (con == null) {
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(urlString, rootString, passString);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        con = getConnection();
    }

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(urlString, rootString, passString);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
}

