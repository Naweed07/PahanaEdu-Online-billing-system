package com.pahanaedu.model;

public class Inventory {
    private int id;
    private String bookName;
    private double price;
    private int stock;

    public Inventory() {
    }

    public Inventory(int id, String bookName, double price, int stock) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.stock = stock;
    }

    public Inventory(String bookName, double price, int stock) {
        this.bookName = bookName;
        this.price = price;
        this.stock = stock;
    }

    // Getters and setters
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
}
