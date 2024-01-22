package com.example.demo3.Services;

import java.sql.SQLException;

public interface UserService {
    boolean registerSalesperson(String name) throws SQLException;

    String registerCustomer(String name, String email);

    void setAccountActive(String LoginLink) throws SQLException;

    String setNewPasswordFromLoginLink(String password, String loginLink) throws SQLException;

    boolean isRegistered(String name) throws SQLException;

    boolean setLoginLink(String name, String loginLink) throws SQLException;

    String isValidCredentials(String username, String password) throws SQLException;

    Object getUserDetail(String loginlink) throws SQLException;

    String getFName();

    String getAddress();
}
