package com.pahanaedu.servlet;

import com.pahanaedu.dao.AdminDAO;
import com.pahanaedu.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Admin admin = adminDAO.login(username, password);
            if (admin != null) {
                HttpSession session = req.getSession();
                session.setAttribute("admin", admin);
                resp.sendRedirect("dashboard.jsp");
            } else {
                req.setAttribute("errorMessage", "Invalid username or password");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error during login", e);
        }
    }
}
