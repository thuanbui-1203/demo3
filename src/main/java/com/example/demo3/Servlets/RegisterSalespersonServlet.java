package com.example.demo3.Servlets;

import com.example.demo3.Services.Implements.UserImpl;
import com.example.demo3.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class RegisterSalespersonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("fullName");

        UserService UserDAO = new UserImpl();
        try {
            UserDAO.registerSalesperson(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            addLoginLink(64, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addLoginLink(int length, String name) throws SQLException {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder randomString = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length()); //random an int number
            char randomChar = chars.charAt(index); // choose a character from chars[index]
            randomString.append(randomChar); // push it into randomString
        }

        UserService UserDAO = new UserImpl();
        UserDAO.setLoginLink(name, randomString.toString());
    }
}
