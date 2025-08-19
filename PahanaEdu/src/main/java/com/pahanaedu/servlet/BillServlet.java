package com.pahanaedu.servlet;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.InventoryDAO;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Inventory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/bill")
public class BillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CustomerDAO customerDAO = new CustomerDAO();
    private InventoryDAO inventoryDAO = new InventoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load inventory to show in billing form
        List<Inventory> inventoryList = inventoryDAO.getAllInventory();
        request.setAttribute("books", inventoryList);
        request.getRequestDispatcher("bill.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");

        // Fetch or create customer
        Customer customer = customerDAO.getCustomerByAccountNumber(accountNumber);
        if (customer == null) {
            customer = new Customer(accountNumber, name, address, telephone, 0);
            customerDAO.addCustomer(customer);
        } else {
            // Update basic details
            customer.setName(name);
            customer.setAddress(address);
            customer.setTelephone(telephone);
        }

        // Load all inventory
        List<Inventory> inventoryList = inventoryDAO.getAllInventory();
        double totalBill = 0;
        List<Inventory> purchasedBooks = new ArrayList<>();

        // Calculate quantities purchased and total bill
        for (Inventory item : inventoryList) {
            String quantityParam = request.getParameter("quantity_" + item.getId());
            int quantity = 0;
            try {
                quantity = Integer.parseInt(quantityParam);
            } catch (NumberFormatException e) {
                quantity = 0;
            }

            if (quantity > 0) {
                item.setQuantity(quantity);
                totalBill += quantity * item.getPrice();
                purchasedBooks.add(item);

                // Update inventory stock
                inventoryDAO.updateStock(item.getId(), quantity);
            }
        }

        // Convert purchased books to a single string for storing in customer table
        StringBuilder purchasedBooksBuilder = new StringBuilder();
        for (Inventory book : purchasedBooks) {
            purchasedBooksBuilder.append(book.getBookName())
                                 .append(" x")
                                 .append(book.getQuantity())
                                 .append(", ");
        }
        String purchasedBooksStr = purchasedBooksBuilder.length() > 0
                                   ? purchasedBooksBuilder.substring(0, purchasedBooksBuilder.length() - 2)
                                   : "";

        // Save purchased books in customer
        customer.setPurchasedBooks(purchasedBooksStr);
        customerDAO.updateCustomer(customer);

        // Forward data to JSP for display
        request.setAttribute("customer", customer);
        request.setAttribute("books", purchasedBooks);
        request.setAttribute("totalBill", totalBill);
        request.getRequestDispatcher("bill.jsp").forward(request, response);
    }
}





//package com.pahanaedu.servlet;
//
//import com.pahanaedu.dao.CustomerDAO;
//import com.pahanaedu.dao.InventoryDAO;
//import com.pahanaedu.model.Customer;
//import com.pahanaedu.model.Inventory;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
////@WebServlet("/bill")
//public class BillServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    private CustomerDAO customerDAO = new CustomerDAO();
//    private InventoryDAO inventoryDAO = new InventoryDAO();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Inventory> inventoryList = inventoryDAO.getAllInventory();
//        request.setAttribute("books", inventoryList);
//        request.getRequestDispatcher("bill.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String accountNumber = request.getParameter("accountNumber");
//        String name = request.getParameter("name");
//        String address = request.getParameter("address");
//        String telephone = request.getParameter("telephone");
//
//        Customer customer = customerDAO.getCustomerByAccountNumber(accountNumber);
//        if (customer == null) {
//            customer = new Customer(accountNumber, name, address, telephone, 0);
//            customerDAO.addCustomer(customer);
//        }
//
//        List<Inventory> inventoryList = inventoryDAO.getAllInventory();
//        double totalBill = 0;
//        List<Inventory> purchasedBooks = new ArrayList<>();
//
//        for (Inventory item : inventoryList) {
//            String quantityParam = request.getParameter("quantity_" + item.getId());
//            int quantity = 0;
//            try {
//                quantity = Integer.parseInt(quantityParam);
//            } catch (NumberFormatException e) {
//                quantity = 0;
//            }
//
//            if (quantity > 0) {
//                item.setQuantity(quantity);
//                totalBill += quantity * item.getPrice();
//                purchasedBooks.add(item);
//
//                // Update stock
//                inventoryDAO.updateStock(item.getId(), quantity);
//            }
//        }
//
//        request.setAttribute("customer", customer);
//        request.setAttribute("books", purchasedBooks);
//        request.setAttribute("totalBill", totalBill);
//        request.getRequestDispatcher("bill.jsp").forward(request, response);
//     // Save purchased books to customer
//        customer.setPurchasedBooks(purchasedBooks);
//        customerDAO.updateCustomerPurchases(customer.getAccountNumber(), purchasedBooks);
//
//    }
//}
//
//




//package com.pahanaedu.servlet;
//
//import com.pahanaedu.dao.CustomerDAO;
//import com.pahanaedu.dao.InventoryDAO;
//import com.pahanaedu.model.Customer;
//import com.pahanaedu.model.Book;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//
//import java.io.IOException;
//import java.util.List;
//
////@WebServlet("/bill")
//public class BillServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    private CustomerDAO customerDAO = new CustomerDAO();
//    private InventoryDAO inventoryDAO = new InventoryDAO();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("bill.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        if (request.getSession().getAttribute("admin") == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        // Get customer info
//        String accountNumber = request.getParameter("accountNumber");
//        String name = request.getParameter("name");
//        String address = request.getParameter("address");
//        String telephone = request.getParameter("telephone");
//
//        int unitsConsumed = 0;
//        try {
//            unitsConsumed = Integer.parseInt(request.getParameter("unitsConsumed"));
//        } catch (NumberFormatException e) {
//            unitsConsumed = 0;
//        }
//
//        Customer customer = customerDAO.getCustomerByAccountNumber(accountNumber);
//        if (customer == null) {
//            customer = new Customer(accountNumber, name, address, telephone, unitsConsumed);
//            customerDAO.addCustomer(customer);
//        } else {
//            customer.setUnitsConsumed(unitsConsumed);
//            customerDAO.updateCustomer(customer);
//        }
//
//        // Get all books
//        List<Book> books = inventoryDAO.getAllInventory();
//
//        // Calculate bill
//        double totalBill = 0;
//        for (Book book : books) {
//            // Get quantity for each book from form (inputs named quantity_1, quantity_2, etc.)
//            int quantity = 0;
//            try {
//                quantity = Integer.parseInt(request.getParameter("quantity_" + book.getId()));
//            } catch (NumberFormatException ignored) {}
//
//            book.setQuantity(quantity);
//            totalBill += book.getPrice() * quantity;
//
//            if(quantity > 0) {
//                inventoryDAO.updateStock(book.getId(), quantity);
//            }
//        }
//
//        request.setAttribute("customer", customer);
//        request.setAttribute("books", books);
//        request.setAttribute("totalBill", totalBill);
//
//        request.getRequestDispatcher("bill.jsp").forward(request, response);
//    }
//}
//
