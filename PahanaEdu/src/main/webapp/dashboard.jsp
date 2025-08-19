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
    
     <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
 
</head>
<body>

<header>
    <h1>Welcome, <%= admin.getUsername() %></h1>
</header>

<a href="logout" class="logout">Logout</a>

<div class="dashboard-header-wrapper">
<div>
<h2 style="margin-top:0px !important;font-size:64px !important;">Dashboard</h2><br/>
</div>
<div>
    <p>Select one of the options above to start managing the system.</p>

</div>
   
</div>

<div style="display:flex;align-items:center;justify-content:center;min-height: 50vh;column-gap: 50px;">
<div>
 <a href="customers" class="op-1">Manage Customers</a>
</div>
<div>
 <a href="inventory" class="op-2">Manage Inventory</a>
</div>
<div>
 <a href="bill" class="op-3">Calculate Bills</a>
</div>

    
   
   
</div>

</body>
</html>
