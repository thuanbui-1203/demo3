package com.example.demo3.Services.Implements;

import com.example.demo3.Services.UserService;
import com.example.demo3.Utilities.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements UserService {
    @Override
    public String registerSalesperson(String name) {
        return null;
    }

    @Override
    public String registerCustomer() {
        return null;
    }

    @Override
    public boolean isRegistered() {
        return false;
    }

    public String isValidCredentials(String username, String password) throws SQLException {
        String role = "";
        Connection conn = Database.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("select * from users where UserName=? and PasswordHash=?");

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                role = rs.getString("UserType");
            }
        } catch (SQLException e) {
            role = "Error: " + e.getMessage();
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public String getUserDetail() throws SQLException {
        String role = "";
        Connection conn = Database.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;


        return null;
    }

    @Override
    public String getFName() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }
}
