package com.example.demo3.Utilities;

import java.sql.*;
import java.util.ResourceBundle;

public class Database {
    private static Connection conn;

    public Database() {
    }

    public static Connection conn() throws SQLException {
        try {
            if (conn == null || conn.isClosed()) {
                ResourceBundle rb = ResourceBundle.getBundle("application");
                String connectionString = rb.getString("db.connectionString");
                String driverName = rb.getString("db.driverName");
                String userName = rb.getString("db.username");
                String password = rb.getString("db.password");

                try {
                    Class.forName(driverName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                conn = DriverManager.getConnection(connectionString, userName, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(PreparedStatement ps) throws SQLException {
        try {
            if (ps != null && !ps.isClosed()) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
