package com.example.demo3.Services.Implements;

import com.example.demo3.Models.UserModel;
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
        UserModel user = new UserModel();
        user.setName(name);
        user.setPassword(PasswordHashing.hashPassword(name));
        user.setMail(name + "@gmail.com");
        user.setRole("salesperson");
        user.setLocked(false);
        ResultSet rs = null;
        boolean isReg = isRegistered(name);

        try {
            if (!isReg) {
                ps1 = conn.prepareStatement
                        ("insert into users (UserName, Email, PasswordHash, UserType, IsActive, IsLocked) " +
                                "values (?, ?, ?, ?, ?, ?)");
                ps1.setString(1, user.getName());
                ps1.setString(2, user.getMail());
                ps1.setString(3, user.getPassword());
                ps1.setString(4, user.getRole());
                ps1.setInt(5, 1);
                ps1.setInt(6, 0);
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
                    "SELECT UserID,  ? , 'DATE_ADD(NOW(), INTERVAL 2 HOUR)' \n" +
                    "FROM javafinal.users \n" +
                    "WHERE UserName = ?; \n");
            ps.setString(1, LoginLink);
            ps.setString(2, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                setAccountActive(name, LoginLink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAccountActive(String name, String LoginLink) throws SQLException {
        Connection conn = Database.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT loginlinks.UserID\n" +
                    "FROM javafinal.loginlinks\n" +
                    "JOIN javafinal.users ON loginlinks.UserID = users.UserID\n" +
                    "WHERE loginlinks.LoginLink = ? AND users.UserName = ?;\n");
            ps.setString(1, LoginLink);
            ps.setString(2, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE javafinal.users\n" +
                        "SET IsActive = 1\n" +
                        "WHERE UserID IN (\n" +
                        "    SELECT ll.UserID\n" +
                        "    FROM javafinal.loginlinks ll\n" +
                        "    JOIN javafinal.users u ON ll.UserID = u.UserID\n" +
                        "    WHERE ll.LoginLink = ?\n" +
                        ");\n");
                ps.setString(1, LoginLink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String setNewPasswordFromLoginLink(String password, String loginLink) throws SQLException {
        Connection conn = Database.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String ph = PasswordHashing.hashPassword(password);

        try {
            ps = conn.prepareStatement("select * from loginlinks where LoginLink = ?");
            ps.setString(1, loginLink);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
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
