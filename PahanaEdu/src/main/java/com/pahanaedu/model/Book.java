package com.pahanaedu.model;

public class Book {
    private int id;
    private String bookName;
    private double price;
    private int stock;
    private int quantity; // number of units purchased

    public Book() {
    }

    public Book(int id, String bookName, double price, int stock) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.stock = stock;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
