<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Customer" %>
<%
    // Check if admin logged in
    if (session.getAttribute("admin") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    Customer editCustomer = (Customer) request.getAttribute("customer");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Customers - Pahana Edu</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f9f9f9; padding: 20px; }
        h2 { margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 30px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #007bff; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        form { background: white; padding: 20px; border-radius: 6px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        input[type="text"], input[type="number"] {
            width: 100%; padding: 8px; margin: 6px 0 15px 0; border: 1px solid #ccc; border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #007bff; color: white; padding: 10px 16px; border: none; border-radius: 4px; cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        a.button {
            background-color: #dc3545; color: white; padding: 6px 12px; text-decoration: none; border-radius: 4px;
        }
        a.button:hover {
            background-color: #c82333;
        }
        .back-dashboard {
            margin-bottom: 20px;
            display: inline-block;
            text-decoration: none;
            color: #007bff;
        }
        .back-dashboard:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<a href="dashboard.jsp" class="back-dashboard">&larr; Back to Dashboard</a>

<h2>Manage Customers</h2>

<table>
    <thead>
    <tr>
        <th>Account Number</th>
        <th>Name</th>
        <th>Address</th>
        <th>Telephone</th>
        <th>Units Consumed</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (customers != null && !customers.isEmpty()) {
            for (Customer c : customers) {
    %>
        <tr>
            <td><%= c.getAccountNumber() %></td>
            <td><%= c.getName() %></td>
            <td><%= c.getAddress() %></td>
            <td><%= c.getTelephone() %></td>
            <td><%= c.getUnitsConsumed() %></td>
            <td>
                <a href="customers?action=edit&accountNumber=<%= c.getAccountNumber() %>">Edit</a> |
                <a href="customers?action=delete&accountNumber=<%= c.getAccountNumber() %>" onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a>
            </td>
        </tr>
    <%
            }
        } else {
    %>
        <tr>
            <td colspan="6" style="text-align:center;">No customers found.</td>
        </tr>
    <%
        }
    %>
    </tbody>
</table>

<h3><%= (editCustomer != null) ? "Edit Customer" : "Add New Customer" %></h3>

<form method="post" action="customers">
    <label>Account Number:</label><br/>
    <input type="text" name="accountNumber" value="<%= (editCustomer != null) ? editCustomer.getAccountNumber() : "" %>" <%= (editCustomer != null) ? "readonly" : "required" %> /><br/>

    <label>Name:</label><br/>
    <input type="text" name="name" value="<%= (editCustomer != null) ? editCustomer.getName() : "" %>" required /><br/>

    <label>Address:</label><br/>
    <input type="text" name="address" value="<%= (editCustomer != null) ? editCustomer.getAddress() : "" %>" required /><br/>

    <label>Telephone:</label><br/>
    <input type="text" name="telephone" value="<%= (editCustomer != null) ? editCustomer.getTelephone() : "" %>" required /><br/>

    <label>Units Consumed:</label><br/>
    <input type="number" name="unitsConsumed" min="0" value="<%= (editCustomer != null) ? editCustomer.getUnitsConsumed() : "0" %>" required /><br/>

    <input type="submit" value="<%= (editCustomer != null) ? "Update Customer" : "Add Customer" %>" />
</form>

</body>
</html>
