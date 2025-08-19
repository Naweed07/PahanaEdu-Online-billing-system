package com.pahanaedu.servlet;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String accountNumber = request.getParameter("accountNumber");

        if ("delete".equalsIgnoreCase(action) && accountNumber != null) {
            customerDAO.deleteCustomer(accountNumber);
            response.sendRedirect("customers");
            return;
        }

        if ("edit".equalsIgnoreCase(action) && accountNumber != null) {
            Customer customer = customerDAO.getCustomerByAccountNumber(accountNumber);
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("customers.jsp").forward(request, response);
            return;
        }

        List<Customer> customers = customerDAO.getAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("customers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        int unitsConsumed = 0;
        try {
            unitsConsumed = Integer.parseInt(request.getParameter("unitsConsumed"));
        } catch (NumberFormatException e) {
            // default 0
        }

        Customer customer = new Customer(accountNumber, name, address, telephone, unitsConsumed);

        if (customerDAO.getCustomerByAccountNumber(accountNumber) != null) {
            customerDAO.updateCustomer(customer);
        } else {
            customerDAO.addCustomer(customer);
        }
        response.sendRedirect("customers");
    }
}
