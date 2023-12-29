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
    <title>Modifier un Livre</title>
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
    <h2>Modifier un Livre</h2>
    <form id="bookForm" method="post" action="GestionLivreServlet">
        <label for="id">ID:</label>
        <input type="text" name="Id" value="<%= book.getId() %>" readonly>

        <label for="titre">Titre:</label>
        <input type="text" name="Titre" value="<%= book.getTitre() %>" required>

        <label for="auteur">Auteur:</label>
        <input type="text" name="Auteur" value="<%= book.getAuteur() %>" required>

        <label for="genre">Genre:</label>
        <select name="Genre" required>
            <option value="Romance" <%= "Romance".equals(book.getGenre()) ? "selected" : "" %>>Romance</option>
            <option value="Mystere" <%= "Mystere".equals(book.getGenre()) ? "selected" : "" %>>Mystere</option>
            <option value="Science-fiction" <%= "Science-fiction".equals(book.getGenre()) ? "selected" : "" %>>Science-fiction</option>
            <option value="Fantasy" <%= "Fantasy".equals(book.getGenre()) ? "selected" : "" %>>Fantasy</option>
        </select>

        <label for="nbExemplaire">Nombre d'exemplaire:</label>
        <input type="number" name="nbExemplaire" value="<%=book.getNbExemplaire() %>" required>

        <button type="submit" name="action" value="update">Modifier</button>
    </form>
</div>

</body>
</html>
