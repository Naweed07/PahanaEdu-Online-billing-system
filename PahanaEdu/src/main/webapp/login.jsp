<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pahana Edu - Admin Login</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    
</head>
<body>

<div class="container">
    <h2>Admin Login</h2>

    <form action="login" method="post">
        <input type="text" name="username" placeholder="Username" required autofocus/>
        <input type="password" name="password" placeholder="Password" required/>
        <input type="submit" value="Login"/>
    </form>

    <div class="error">
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
                out.print(errorMessage);
            }
        %>
    </div>

    <div class="register-link">
        <a href="register.jsp">Don't have an account? Register here</a>
    </div>
</div>

</body>
</html>
