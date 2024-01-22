package com.example.demo3.Services.Implements;

import com.example.demo3.Models.UserModel;
import com.example.demo3.Services.UserService;
import com.example.demo3.Utilities.Database;
import com.example.demo3.Utilities.MailMessage;
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
                        ("insert into users (UserName, Email, PasswordHash, UserType) " +
                                "values (?, ?, ?, ?)");
                ps1.setString(1, user.getName());
                ps1.setString(2, user.getMail());
                ps1.setString(3, user.getPassword());
                ps1.setString(4, user.getRole());
                ps1.executeUpdate();
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
    public UserModel getUserDetail(String loginlink) throws SQLException {
        UserModel user = null;
        Connection conn = Database.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from users where LoginLink = ?");
            ps.setString(1, loginlink);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new UserModel();

                user.setName(rs.getString("UserName"));
                user.setPassword(rs.getString("PasswordHash"));
                user.setRole(rs.getString("UserType"));
                user.setMail(rs.getString("Email"));
                user.setLoginLink(rs.getString("LoginLink"));
                user.setLocked(false);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean setLoginLink(String name, String LoginLink) throws SQLException {
        Connection conn = Database.conn();
        PreparedStatement ps = null;
        String email = name + "@gmail.com";

        try {
            ps = conn.prepareStatement("UPDATE users SET LoginLink = ? WHERE UserName = ?");
            ps.setString(1, LoginLink);
            ps.setString(2, name);
            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0) {
//                setAccountActive(name, LoginLink);
                MailMessage.registrationSuccess(name, email, LoginLink);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void setAccountActive(String LoginLink) throws SQLException {
        Connection conn = Database.conn();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM javafinal.users WHERE LoginLink = ?");
            ps.setString(1, LoginLink);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE users SET IsActive = 1 WHERE LoginLink = ?");
                ps.setString(1, LoginLink);
                ps.executeUpdate();
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
            ps = conn.prepareStatement("select * from users where LoginLink = ?");
            ps.setString(1, loginLink);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("update users set PasswordHash = ? where LoginLink = ?");
                ps.setString(1, ph);
                ps.setString(2, loginLink);
                ps.executeUpdate();

                setAccountActive(loginLink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Set new password successfully";
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
