<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-image: url('images/background.jpg');
        background-size: cover;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    
    form {
        max-width: 300px;
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
    
    
</style>
</head>
<body>
<form method="post" action="Register" >
        Nom: <input type="text" name="Nom" /><br/>
        Prenom: <input type="text" name="Prenom" /><br/>
        Email: <input type="email" name="E-mail" /><br/>
        Password: <input type="password" name="MotDePasse" /><br/>
        CNF_Password: <input type="password" name="CNF_MDP" /><br/>
        <input type="submit" value="Register" />
    </form>
</body>
</html>