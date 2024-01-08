package com.example.demo3.Services;

import java.sql.SQLException;

public interface UserService {
    String registerSalesperson(String name);

    String registerCustomer();

    boolean isRegistered();

    String isValidCredentials(String username, String password) throws SQLException;

    String getUserDetail() throws SQLException;

    String getFName();

    String getAddress();
}
