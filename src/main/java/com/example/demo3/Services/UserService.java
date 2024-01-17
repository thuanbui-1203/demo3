package com.example.demo3.Services;

import java.sql.SQLException;

public interface UserService {
    boolean registerSalesperson(String name) throws SQLException;

    String registerCustomer(String name, String email);

    void setAccountActive(String name, String LoginLink) throws SQLException;

    String setNewPasswordFromLoginLink(String password, String loginLink) throws SQLException;

    boolean isRegistered(String name) throws SQLException;

    void setLoginLink(String name, String loginLink) throws SQLException;

    String isValidCredentials(String username, String password) throws SQLException;

    String getUserDetail() throws SQLException;

    String getFName();

    String getAddress();
}
