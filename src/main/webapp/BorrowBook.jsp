<%@page import="metier.BookMetier"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="metier.entities.BookModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>emprunter un Livre</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
       	background-image: url('images/background.jpg'); 
        background-size: cover;
        margin: 0;
        padding: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }

    input, select {
        width: 100%;
        padding: 8px;
        margin-bottom: 16px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    #editBookForm {
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 300px;
    }

    label {
        display: block;
        margin-bottom: 8px;
        font-weight: bold;
    }

    input {
        width: 100%;
        padding: 8px;
        margin-bottom: 16px;
        box-sizing: border-box;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    button {
        background-color: #4caf50;
        color: #fff;
        padding: 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button:hover {
        background-color: #45a049;
    }
</style>
</head>
   <% 
        
       String bookTitle = request.getParameter("title"); 
   
        BookMetier bookMetier = new BookMetier();
        BookModel book = bookMetier.getBookByTitle(bookTitle);
        
       
    %>

<body>

<div id="editBookForm">
    <h2>Emprunter un Livre</h2>
    <form id="bookForm" method="post" action="GestionLivreServlet">
         <label for="id"></label>
        <input type="number" name="Id" value="<%= book.getId() %>" readonly>
        
        <label for="titre">Titre:</label>
        <input type="text" name="Titre" value="<%= book.getTitre() %>" readonly="readonly">

        <label for="auteur">Auteur:</label>
        <input type="text" name="Auteur" value="<%= book.getAuteur() %>" readonly="readonly">

        

        <label for="nbExemplaire">Nombre d'exemplaire disponibles:</label>
        <input type="number" name="nbExemplaire" value="<%=book.getNbExemplaire() %>" readonly="readonly">
        
        <label for="nbExemplaire2">Nombre d'exemplaire à emprunter :</label>
        <input type="number" name="nbExemplaire2" required="required">
        
        <label for="nbJour">Nombre de jours:</label>
        <input type="number" name="nbJour" required>
        
        <button type="submit" name="action" value="borrow">Emprunter</button>
    </form>
</div>

</body>
</html>
