package com.pahanaedu.dao;

import com.pahanaedu.config.DBConnection;
import com.pahanaedu.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (account_number, name, address, telephone, units_consumed) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getAccountNumber());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getTelephone());
            stmt.setInt(5, customer.getUnitsConsumed());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET name=?, address=?, telephone=?, units_consumed=? WHERE account_number=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getTelephone());
            stmt.setInt(4, customer.getUnitsConsumed());
            stmt.setString(5, customer.getAccountNumber());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(String accountNumber) {
        String sql = "DELETE FROM customer WHERE account_number=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, accountNumber);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Customer getCustomerByAccountNumber(String accountNumber) {
        String sql = "SELECT * FROM customer WHERE account_number=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setAccountNumber(rs.getString("account_number"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setUnitsConsumed(rs.getInt("units_consumed"));
                return customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setAccountNumber(rs.getString("account_number"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setUnitsConsumed(rs.getInt("units_consumed"));
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}






