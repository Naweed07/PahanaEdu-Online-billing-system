<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.model.Customer" %>
<%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Customer customer = (Customer) request.getAttribute("customer");
    Double totalBill = (Double) request.getAttribute("totalBill");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Calculate Bill - Pahana Edu</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f9f9f9; padding: 20px; }
        h2 { margin-bottom: 20px; }
        form { background: white; padding: 20px; border-radius: 6px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 500px; margin-bottom: 20px; }
        input[type="text"], input[type="number"] {
            width: 100%; padding: 8px; margin: 6px 0 15px 0; border: 1px solid #ccc; border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #007bff; color: white; padding: 10px 16px; border: none; border-radius: 4px; cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
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
        .bill-result {
            background: white;
            padding: 20px;
            border-radius: 6px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 500px;
        }
    </style>
</head>
<body>

<a href="dashboard.jsp" class="back-dashboard">&larr; Back to Dashboard</a>

<h2>Calculate Customer Bill</h2>

<form method="post" action="bill">
    <label>Account Number:</label><br/>
    <input type="text" name="accountNumber" value="<%= (customer != null) ? customer.getAccountNumber() : "" %>" required autofocus/><br/>

    <label>Name:</label><br/>
    <input type="text" name="name" value="<%= (customer != null) ? customer.getName() : "" %>" /><br/>

    <label>Address:</label><br/>
    <input type="text" name="address" value="<%= (customer != null) ? customer.getAddress() : "" %>" /><br/>

    <label>Telephone:</label><br/>
    <input type="text" name="telephone" value="<%= (customer != null) ? customer.getTelephone() : "" %>" /><br/>

    <label>Units Consumed:</label><br/>
    <input type="number" name="unitsConsumed" min="0" value="<%= (customer != null) ? customer.getUnitsConsumed() : "0" %>" required/><br/>

    <input type="submit" value="Calculate Bill"/>
</form>

<%
    if (customer != null && totalBill != null) {
%>
<div class="bill-result">
    <h3>Billing Details</h3>
    <p><strong>Account Number:</strong> <%= customer.getAccountNumber() %></p>
    <p><strong>Name:</strong> <%= customer.getName() %></p>
    <p><strong>Units Consumed:</strong> <%= customer.getUnitsConsumed() %></p>
    <p><strong>Total Bill:</strong> $<%= String.format("%.2f", totalBill) %></p>
</div>
<%
    }
%>

</body>
</html>
