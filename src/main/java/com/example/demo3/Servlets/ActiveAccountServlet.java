package com.example.demo3.Servlets;

import com.example.demo3.Models.UserModel;
import com.example.demo3.Services.Implements.UserImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ActiveAccountServlet")
public class ActiveAccountServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String status = "Login link expired";
        // Retrieve the login link parameter from the request
        String loginLink = request.getParameter("loginLink");

        // Use the loginLink as needed (e.g., for verification)

        // Retrieve other parameters from the form
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

        // Validate the new password and perform activation logic
        // (Verify loginLink, validate newPassword, update password in the database, etc.)
        if (newPassword.equals(confirmNewPassword)) {
            UserImpl UserDAO = new UserImpl();
            UserModel user = UserDAO.getUserDetail(loginLink);

            if (loginLink.equals(user.getLoginLink())) {
                UserDAO.setNewPasswordFromLoginLink(newPassword, loginLink);
                response.sendRedirect("activationResult.jsp?success=true");
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("activeAccount.jsp?message=" + status);
        }

        // Redirect to a success or error page
        response.sendRedirect("activationResult.jsp?success=true");
    }
}
