

package com.pahanaedu.servlet;

import com.pahanaedu.dao.AdminDAO;
import com.pahanaedu.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Admin admin = new Admin(username, password);

        try {
            boolean success = adminDAO.register(admin);
            if (success) {
                resp.sendRedirect("login.jsp");
            } else {
                req.setAttribute("errorMessage", "Registration failed, try again");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                req.setAttribute("errorMessage", "Username already exists");
            } else {
                req.setAttribute("errorMessage", "Database error: " + e.getMessage());
            }
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
