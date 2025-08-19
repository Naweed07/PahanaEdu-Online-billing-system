<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.model.Customer" %>
<%@ page import="com.pahanaedu.model.Inventory" %>
<%@ page import="java.util.List" %>

<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Customer customer = (Customer) request.getAttribute("customer");
    Double totalBill = (Double) request.getAttribute("totalBill");
    List<Inventory> books = (List<Inventory>) request.getAttribute("books");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Calculate Bill - Pahana Edu</title>
    <style>
        body { font-family: Arial; background: #f9f9f9; padding: 20px; }
        h2 { margin-bottom: 20px; }
        form, .bill-result { background: #fff; padding: 20px; border-radius: 6px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 700px; margin-bottom: 20px; }
        input[type="text"], input[type="number"] { width: 100%; padding: 8px; margin: 6px 0 15px; border: 1px solid #ccc; border-radius: 4px; }
        input[type="submit"] { background-color: #007bff; color: #fff; padding: 10px 16px; border: none; border-radius: 4px; cursor: pointer; }
        input[type="submit"]:hover { background-color: #0056b3; }
        .back-dashboard { display: inline-block; margin-bottom: 20px; color: #007bff; text-decoration: none; }
        .back-dashboard:hover { text-decoration: underline; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>

<a href="dashboard.jsp" class="back-dashboard">&larr; Back to Dashboard</a>

<h2>Calculate Customer Bill</h2>

<form method="post" action="bill">
    <label>Account Number:</label>
    <input type="text" name="accountNumber" value="<%= (customer != null) ? customer.getAccountNumber() : "" %>" required autofocus/>

    <label>Name:</label>
    <input type="text" name="name" value="<%= (customer != null) ? customer.getName() : "" %>" />

    <label>Address:</label>
    <input type="text" name="address" value="<%= (customer != null) ? customer.getAddress() : "" %>" />

    <label>Telephone:</label>
    <input type="text" name="telephone" value="<%= (customer != null) ? customer.getTelephone() : "" %>" />

    <h3>Select Books & Quantity</h3>
    <%
        if (books != null && !books.isEmpty()) {
            for (Inventory book : books) {
    %>
        <label><%= book.getBookName() %> ($<%= book.getPrice() %>) - Stock: <%= book.getStock() %></label>
        <input type="number" name="quantity_<%= book.getId() %>" min="0" max="<%= book.getStock() %>" value="0"/>
    <%
            }
        }
    %>

    <input type="submit" value="Calculate Bill"/>
</form>

<%
    if (customer != null && totalBill != null && books != null && !books.isEmpty()) {
%>
<div class="bill-result">
    <h3>Billing Details</h3>
    <p><strong>Account Number:</strong> <%= customer.getAccountNumber() %></p>
    <p><strong>Name:</strong> <%= customer.getName() %></p>

    <h4>Books Purchased</h4>
    <table>
        <tr>
            <th>Book Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
        </tr>
        <%
            for (Inventory book : books) {
                double subtotal = book.getPrice() * book.getQuantity();
                if (book.getQuantity() > 0) {
        %>
        <tr>
            <td><%= book.getBookName() %></td>
            <td>$<%= String.format("%.2f", book.getPrice()) %></td>
            <td><%= book.getQuantity() %></td>
            <td>$<%= String.format("%.2f", subtotal) %></td>
        </tr>
        <%
                }
            }
        %>
    </table>

    <p><strong>Total Bill:</strong> $<%= String.format("%.2f", totalBill) %></p>
</div>
<%
    }
%>

</body>
</html>





