<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ajouter un Livre</title>
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

        #addBookForm {
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
<body>

<div id="addBookForm" >
    <h2>Ajouter un Livre</h2>
    <form id="bookForm" method="post" action="GestionLivreServlet">
        <label for="titre">Titre:</label>
        <input type="text" name="Titre" required>

        <label for="auteur">Auteur:</label>
        <input type="text" name="Auteur" required>

         <label for="genre">Genre:</label>
        <select name="Genre" required>
            <option value="Romance">Romance</option>
            <option value="Mystere">Mystere</option>
            <option value="Science-fiction">Science-fiction</option>
            <option value="Fantasy">Fantasy</option>
        </select>

        <label for="nbExemplaire">Nombre d'exemplaire:</label>
        <input type="number" name="nbExemplaire" required>

        <button type="submit" name="action" value="add" >Ajouter</button>
    </form>
</div>

</body>
</html>