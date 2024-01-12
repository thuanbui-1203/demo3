package com.example.demo3.Services.Implements;

import com.example.demo3.Services.UserService;
import com.example.demo3.Utilities.Database;
import com.example.demo3.Utilities.PasswordHashing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements UserService {
    @Override
    public boolean registerSalesperson(String name) throws SQLException {
        Connection conn = Database.conn();
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        boolean isReg = isRegistered(name);

        String ph = PasswordHashing.hashPassword(name);

        try {
            if (!isReg) {
                ps1 = conn.prepareStatement
                        ("insert into users (UserName, Email, PasswordHash, UserType, IsActive, IsLocked) " +
                                "values (?, ?, ?, ?, ?, ?)");
                ps1.setString(1, name);
                ps1.setString(2, name + "@gmail.com");
                ps1.setString(3, ph);
                ps1.setString(4, "salesperson");
                ps1.setString(5, "0");
                ps1.setString(6, "0");
                rs = ps1.executeQuery();

                Database.closeConnection(ps1);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.closeConnection(ps1);
        return false;
    }

    @Override
    public String registerCustomer(String name, String email) {
        return null;
    }

    @Override
    public boolean isRegistered(String name) throws SQLException {
        PreparedStatement ps;
        ResultSet rs = null;
        Connection conn = Database.conn();

        ps = conn.prepareStatement("select * from users where UserName = ?");
        ps.setString(1, name);

        try {
            rs = ps.executeQuery();
            if (rs.next()) {
                Database.closeConnection(ps);
                Database.closeConnection(rs);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Database.closeConnection(ps);
        Database.closeConnection(rs);
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
        Database.closeConnection(ps);
        Database.closeConnection(rs);
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
    public void setLoginLink(String name, String LoginLink) throws SQLException {
        Connection conn = Database.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("INSERT INTO javafinal.loginlinks (UserID, LoginLink, ExpiryTime) \n" +
                    "SELECT UserID, '" + LoginLink + "', 'DATE_ADD(NOW(), INTERVAL 2 HOUR)' \n" +
                    "FROM javafinal.users \n" +
                    "WHERE UserName = '" + name + "'; \n");
            rs = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
