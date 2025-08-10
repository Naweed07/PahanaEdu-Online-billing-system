<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pahana Edu - Admin Login</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; }
        .container { max-width: 400px; margin: 80px auto; padding: 20px; background: #fff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        h2 { text-align: center; margin-bottom: 20px; }
        input[type="text"], input[type="password"] {
            width: 100%; padding: 10px; margin-bottom: 15px; border: 1px solid #ddd; border-radius: 4px;
        }
        input[type="submit"] {
            width: 100%; background: #007bff; border: none; padding: 10px; color: white; font-size: 16px; cursor: pointer; border-radius: 4px;
        }
        input[type="submit"]:hover {
            background: #0056b3;
        }
        .error { color: red; margin-bottom: 15px; text-align: center; }
        .register-link { text-align: center; margin-top: 15px; }
    </style>
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
