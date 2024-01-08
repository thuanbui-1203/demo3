package com.example.demo3.Servlets;

import com.example.demo3.Services.Implements.UserImpl;
import com.example.demo3.Services.UserService;
import com.example.demo3.Utilities.PasswordHashing;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService UserDAO = new UserImpl();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String hashedPassword = PasswordHashing.hashPassword(password);

        String role = null;

        try {
            role = UserDAO.isValidCredentials(username, hashedPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // login as admin
        if (role.equals("admin")) {
            req.setAttribute("role", role);
            RequestDispatcher rd = req.getRequestDispatcher("AdminFunction.jsp");

            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("role", role);

            rd.include(req, resp);
        }
        // login as salesperson
        else if (role.equals("salesperson")) {
            req.setAttribute("role", role);
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");

            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            session.setAttribute("role", role);
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("ProductList.jsp");
        }
    }
}
