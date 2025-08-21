package com.pahanaedu.model;

import java.util.List;

public class Customer {
    private String accountNumber;
    private String name;
    private String address;
    private String telephone;
    private int unitsConsumed;

    // Store purchased books as a String for DB
    private String purchasedBooks;

    public Customer() {}

    public Customer(String accountNumber, String name, String address, String telephone, int unitsConsumed) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.unitsConsumed = unitsConsumed;
        this.purchasedBooks = "";
    }

    // Getters & Setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public int getUnitsConsumed() { return unitsConsumed; }
    public void setUnitsConsumed(int unitsConsumed) { this.unitsConsumed = unitsConsumed; }

    public String getPurchasedBooks() { return purchasedBooks; }
    public void setPurchasedBooks(String purchasedBooks) { this.purchasedBooks = purchasedBooks; }
}






