package com.bank.servlet;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNo = request.getParameter("account_no");
        String currentPassword = request.getParameter("current_password");
        String newPassword = request.getParameter("new_password");

        try {
            Customer customer = customerDAO.login(accountNo, currentPassword);
            if (customer != null) {
                boolean isUpdated = customerDAO.updatePassword(accountNo, newPassword);
                if (isUpdated) {
                    response.sendRedirect("change-password.jsp?message=Password changed successfully");
                } else {
                    response.sendRedirect("change-password.jsp?error=Failed to change password");
                }
            } else {
                response.sendRedirect("change-password.jsp?error=Invalid current password");
            }
        } 
        catch ( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Database connection problem");
        }
    }
}
