<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.model.Admin" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Pahana Edu - Admin Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f9f9f9; }
        header { background: #007bff; color: white; padding: 15px; text-align: center; }
        nav {
            background: #343a40;
            padding: 10px;
            display: flex;
            justify-content: center;
            gap: 20px;
        }
        nav a {
            color: white;
            text-decoration: none;
            padding: 8px 15px;
            border-radius: 4px;
        }
        nav a:hover {
            background: #495057;
        }
        main {
            max-width: 900px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 12px rgba(0,0,0,0.1);
            text-align: center;
        }
        .logout {
            position: absolute;
            top: 20px;
            right: 20px;
            background: #dc3545;
            color: white;
            padding: 8px 12px;
            text-decoration: none;
            border-radius: 4px;
        }
        .logout:hover {
            background: #c82333;
        }
    </style>
</head>
<body>

<header>
    <h1>Welcome, <%= admin.getUsername() %></h1>
</header>

<a href="logout" class="logout">Logout</a>

<nav>
    <a href="customers">Manage Customers</a>
    <a href="inventory">Manage Inventory</a>
    <a href="bill">Calculate Bills</a>
</nav>

<main>
    <h2>Dashboard</h2>
    <p>Select one of the options above to start managing the system.</p>
</main>

</body>
</html>
