<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Help - PahaanEdu</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 40px;
        background-color: #f9f9f9;
        color: #333;
    }
    h1 {
        color: #0073e6;
    }
    ul {
        line-height: 1.8;
    }
    .container {
        background: #fff;
        padding: 25px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        max-width: 800px;
        margin: auto;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>Welcome to PahaanEdu Help</h1>
        <p>
            <b>PahaanEdu</b> is a Book Inventory and Billing Management System 
            that helps administrators manage books, customer records, and generate bills easily.
        </p>

        <h2>System Features</h2>
        <ul>
            <li><b>Login/Register:</b> Secure access for administrators.</li>
            <li><b>Manage Books:</b> Add, edit, delete, or view books in the inventory.</li>
            <li><b>Manage Customers:</b> Store and update customer details.</li>
            <li><b>Generate Bill:</b> Enter account number, customer name, and number of books purchased to calculate the bill.</li>
        </ul>

        <h2>How to Use</h2>
        <ol>
            <li>Login with your administrator account.</li>
            <li>Navigate to <b>Books</b> to add or manage inventory.</li>
            <li>Go to <b>Customers</b> to add customer details.</li>
            <li>Open <b>Billing</b>, enter the required details, and click <b>Calculate Bill</b>.</li>
            <li>The system will fetch book prices and display the total amount.</li>
        </ol>

        <p>
            If you face any issues, please contact the system administrator.
        </p>
    </div>
</body>
</html>
