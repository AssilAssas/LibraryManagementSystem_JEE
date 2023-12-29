<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="metier.entities.EmpruntModel" %>
<%@ page import="metier.entities.UserModel" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Your head content here -->

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library Management System </title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-image: url('images/background.jpg'); 
       		background-size: cover;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .dashboard {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            border-radius: 5px;
        }

        h1 {
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        .btn {
            display: inline-block;
            padding: 16px 32px;
            text-align: center;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
            cursor: pointer;
            margin-right: 10px;
        }

        .add-btn, .back-btn {
            background-color: #4CAF50;
            color: #fff;
        }

        .search-bar {
            margin-top: 20px;
            overflow: hidden;
            text-align: center;
        }

        input[type="text"] {
            padding: 10px;
            width: 200px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
    </style>
    <script>
        function showAddForm() {
            // Redirect to the AddBook.jsp page
            window.location.href = 'AddBook.jsp';
        }
        function showUpdateForm(bookTitle){
        	window.location.href= 'UpdateBook.jsp?title=' + encodeURIComponent(bookTitle); 
        	
        }
        function showBorrowForm(bookTitle){
        	window.location.href= 'BorrowBook.jsp?title=' + encodeURIComponent(bookTitle); 
        	
        }
        function showHistoryPage(){
        	window.location.href= 'history.jsp'; 
        	
        }
    </script>
</head>
<body>

    welcome
    <% UserModel model = (UserModel) session.getAttribute("UserModel"); %>
    <% if (model != null) { %>
        <%= model.getEmail() %>
    <% } %>
    <div class="dashboard">
        <h1>Library Management System</h1>

        <div class="search-bar">
            <form action="GestionLivreServlet" method="post">
            	<button type="button" class="add-btn" onclick="window.location.href = '/bibApplication/GestionLivreServlet'" >Back Home</button>
            </form>
        </div> 
        
        <table>
           <tr>
    <th>User Name</th>
    <th>Book Title</th>
    <th>Number Borrowed</th>
    <th>Date Borrowed</th>
    <th>Date Returned</th>
    <th>Status</th>
</tr>

<%
List<EmpruntModel> displayEmprunts;

displayEmprunts = (List<EmpruntModel>) session.getAttribute("allEmprunts");

if (displayEmprunts != null) {
    Iterator<EmpruntModel> iterator = displayEmprunts.iterator();
    while (iterator.hasNext()) {
        EmpruntModel emprunt = iterator.next();
%>
<!-- Update the table rows -->
<tr>
    <td><%= emprunt.getUserName() %></td>
    <td><%= emprunt.getBookTitle() %></td>
    <td><%= emprunt.getNbEmpruntes() %></td>
    <td><%= emprunt.getDateEmprunt() %></td>
    <td><%= emprunt.getDateRetour() %></td>
    <td><%= emprunt.getStatueActuelEmp() %></td>
</tr>
<%
    }
}
%>
        </table>
    </div>
</body>
</html>
