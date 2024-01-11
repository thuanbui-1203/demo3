package com.example.demo3.Services;

import java.sql.SQLException;

public interface UserService {
    boolean registerSalesperson(String name) throws SQLException;

    String registerCustomer(String name, String email);

    boolean isRegistered(String name) throws SQLException;

    String isValidCredentials(String username, String password) throws SQLException;

    String getUserDetail() throws SQLException;

    String getFName();

    String getAddress();
}
