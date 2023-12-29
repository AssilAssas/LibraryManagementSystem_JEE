<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="metier.UserMetier" %>

<html>
<head>
    <title>Login Page</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-image: url('images/background.jpg'); 
        background-size: cover;
        margin: 0;
        padding: 0;
    }
    
    form {
        max-width: 300px;
        margin: 50px auto;
        padding: 20px;
        background-color: rgba(255, 255, 255, 0.8); 
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
    }
    
    form input {
        width: 100%;
        margin-bottom: 10px;
        padding: 8px;
        box-sizing: border-box;
    }
    
    form input[type="submit"] {
        background-color: #4caf50;
        color: #fff;
        cursor: pointer;
    }
    
    form a {
        display: block;
        text-align: center;
        margin-top: 15px;
        color: #333;
        text-decoration: none;
    }
    
    
</style>
</head>
<body>

    <form method="post" action="loginServlet">
        Email: <input type="text" name="E-mail" /><br/>
        Password: <input type="password" name="MotDePasse" /><br/>
        <input type="submit" value="Login" /><br>
    
    <a href="Register.jsp">Register Here</a>
    </form>

</body>
</html>
