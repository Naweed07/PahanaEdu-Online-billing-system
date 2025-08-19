package com.pahanaedu.servlet;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

//@WebServlet("/bill")
public class BillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Just forward to billing page with empty or default attributes
        request.getRequestDispatcher("bill.jsp").forward(request, response);
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

        Customer existingCustomer = customerDAO.getCustomerByAccountNumber(accountNumber);

        if (existingCustomer == null) {
            // New customer: add customer with units consumed
            Customer newCustomer = new Customer(accountNumber, name, address, telephone, unitsConsumed);
            customerDAO.addCustomer(newCustomer);
            request.setAttribute("customer", newCustomer);
        } else {
            // Existing customer: update units consumed (overwrite with submitted value)
            existingCustomer.setUnitsConsumed(unitsConsumed);
            customerDAO.updateCustomer(existingCustomer);
            request.setAttribute("customer", existingCustomer);
        }

        // Here you can calculate bill (for example: unitsConsumed * rate)
        double ratePerUnit = 5.0; // example fixed rate
        double totalBill = unitsConsumed * ratePerUnit;
        request.setAttribute("totalBill", totalBill);

        request.getRequestDispatcher("bill.jsp").forward(request, response);
    }
}
