package com.pahanaedu.dao;

import com.pahanaedu.config.DBConnection;
import com.pahanaedu.model.Inventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    public boolean addInventory(Inventory item) {
        String sql = "INSERT INTO inventory (book_name, price, stock) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getBookName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getStock());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateInventory(Inventory item) {
        String sql = "UPDATE inventory SET book_name=?, price=?, stock=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getBookName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getStock());
            stmt.setInt(4, item.getId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteInventory(int id) {
        String sql = "DELETE FROM inventory WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Inventory getInventoryById(int id) {
        String sql = "SELECT * FROM inventory WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Inventory item = new Inventory();
                item.setId(rs.getInt("id"));
                item.setBookName(rs.getString("book_name"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));
                return item;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Inventory> getAllInventory() {
        List<Inventory> items = new ArrayList<>();
        String sql = "SELECT * FROM inventory";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Inventory item = new Inventory();
                item.setId(rs.getInt("id"));
                item.setBookName(rs.getString("book_name"));
                item.setPrice(rs.getDouble("price"));
                item.setStock(rs.getInt("stock"));
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    // NEW METHOD: update stock after billing
    public boolean updateStock(int bookId, int quantitySold) {
        String sql = "UPDATE inventory SET stock = stock - ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantitySold);
            stmt.setInt(2, bookId);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
