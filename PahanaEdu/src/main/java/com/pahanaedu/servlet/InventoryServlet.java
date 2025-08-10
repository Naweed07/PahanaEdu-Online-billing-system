package com.pahanaedu.servlet;

import com.pahanaedu.dao.InventoryDAO;
import com.pahanaedu.model.Inventory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private InventoryDAO inventoryDAO = new InventoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("delete".equalsIgnoreCase(action) && idStr != null) {
            int id = Integer.parseInt(idStr);
            inventoryDAO.deleteInventory(id);
            response.sendRedirect("inventory");
            return;
        }

        if ("edit".equalsIgnoreCase(action) && idStr != null) {
            int id = Integer.parseInt(idStr);
            Inventory item = inventoryDAO.getInventoryById(id);
            request.setAttribute("item", item);
            request.getRequestDispatcher("inventory.jsp").forward(request, response);
            return;
        }

        List<Inventory> items = inventoryDAO.getAllInventory();
        request.setAttribute("items", items);
        request.getRequestDispatcher("inventory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String bookName = request.getParameter("bookName");
        double price = 0.0;
        int stock = 0;

        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (NumberFormatException e) {
            // default 0.0
        }

        try {
            stock = Integer.parseInt(request.getParameter("stock"));
        } catch (NumberFormatException e) {
            // default 0
        }

        Inventory item = new Inventory();
        item.setBookName(bookName);
        item.setPrice(price);
        item.setStock(stock);

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            item.setId(id);
            inventoryDAO.updateInventory(item);
        } else {
            inventoryDAO.addInventory(item);
        }

        response.sendRedirect("inventory");
    }
}
