<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Inventory" %>
<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Inventory> items = (List<Inventory>) request.getAttribute("items");
    Inventory editItem = (Inventory) request.getAttribute("item");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Inventory - Pahana Edu</title>
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
            padding:10px 15px;
            background-color:#6c757d;
            color:#fff;
            font-weight:600;
            border-radius:10px;
        }
        .back-dashboard:hover {
            background-color:#cfd3d7;
        }
    </style>
</head>
<body>

<a href="dashboard.jsp" class="back-dashboard">&larr; Back to Dashboard</a>

<h2>Manage Inventory</h2>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Book Name</th>
        <th>Price</th>
        <th>Stock</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (items != null && !items.isEmpty()) {
            for (Inventory i : items) {
    %>
        <tr>
            <td><%= i.getId() %></td>
            <td><%= i.getBookName() %></td>
            <td>$<%= String.format("%.2f", i.getPrice()) %></td>
            <td><%= i.getStock() %></td>
            <td>
                <a href="inventory?action=edit&id=<%= i.getId() %>">Edit</a> |
                <a href="inventory?action=delete&id=<%= i.getId() %>" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
            </td>
        </tr>
    <%
            }
        } else {
    %>
        <tr>
            <td colspan="5" style="text-align:center;">No inventory items found.</td>
        </tr>
    <%
        }
    %>
    </tbody>
</table>

<h3><%= (editItem != null) ? "Edit Inventory Item" : "Add New Inventory Item" %></h3>

<form method="post" action="inventory">
    <% if (editItem != null) { %>
        <input type="hidden" name="id" value="<%= editItem.getId() %>"/>
    <% } %>

    <label>Book Name:</label><br/>
    <input type="text" name="bookName" value="<%= (editItem != null) ? editItem.getBookName() : "" %>" required/><br/>

    <label>Price:</label><br/>
    <input type="number" name="price" step="0.01" min="0" value="<%= (editItem != null) ? editItem.getPrice() : "0.00" %>" required/><br/>

    <label>Stock:</label><br/>
    <input type="number" name="stock" min="0" value="<%= (editItem != null) ? editItem.getStock() : "0" %>" required/><br/>

    <input type="submit" value="<%= (editItem != null) ? "Update Item" : "Add Item" %>" />
</form>

</body>
</html>
