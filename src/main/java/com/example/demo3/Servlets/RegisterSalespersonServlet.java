package com.example.demo3.Servlets;

import com.example.demo3.Models.UserModel;
import com.example.demo3.Services.Implements.UserImpl;
import com.example.demo3.Services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterSalespersonServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("ManagerRegisterSalesperson.jsp");

        String name = req.getParameter("fullName");

        UserModel regUser = new UserModel();
        regUser.setName(name);

        UserService UserDAO = new UserImpl();
        try {
            UserDAO.registerSalesperson(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
