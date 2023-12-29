<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="metier.entities.BookModel" %>
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
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            background-color: #007bff;
            color: #fff;
            border-radius: 4px;
            transition: background-color 0.3s;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .search-bar {
            margin-top: 20px;
            overflow: hidden;
        }

        input[type="text"] {
            padding: 10px;
            width: 200px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .search-btn {
            padding: 10px 16px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .add-btn {
            padding: 10px 16px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: auto;
            float: right;  
        }
        .disconnect-btn {
		    padding: 10px 16px;
		    background-color: #ff4444; /* Red color for disconnect button */
		    color: #fff;
		    border: none;
		    border-radius: 4px;
		    cursor: pointer;
		    margin-left: 10px; /* Adjust the margin as needed */
		}
		
		.disconnect-btn:hover {
		    background-color: #cc0000; /* Darker red on hover */
		}
        .history-btn {
		    padding: 10px 16px;
		    background-color: #808080; 
		    color: #fff;
		    border: none;
		    border-radius: 4px;
		    cursor: pointer;
		    margin-left: 10px; /* Adjust the margin as needed */
		}
		
		.history-btn:hover {
		    background-color: #a9a9a9; 
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

   
    <div class="dashboard">
        <h1>Library Management System</h1>

        <div class="search-bar">
            <form action="GestionLivreServlet" method="post">
                <input type="text" name="search" placeholder="Enter book title">
                <button type="submit" name="action" value="search" class="search-btn">Search</button>
                <button type="button" class="add-btn" onclick="showAddForm()">Add your Book</button>
                <button type="button" class="disconnect-btn" onclick="window.location.href = '/bibApplication/LogoutServlet'">Logout</button>
                <button type="button" class="history-btn" onclick="window.location.href = '/bibApplication/HistoryServlet'">Show Mouvements History</button>

            </form>
        </div> 
        
        <table>
            <tr>
                <th>Title</th>
                <th>Author</th>
                <th>Genre</th>
                <th>NbExemplaire</th>
                <th>Actions</th>
            </tr>
           
            <%
            List<BookModel> displayBooks;
            boolean formSubmitted = "search".equals(request.getParameter("action"));
            boolean historySubmitted = "history".equals(request.getParameter("action"));
            if (historySubmitted) {
                // Redirect to HistoryServlet when the history button is submitted
                response.sendRedirect("HistoryServlet");
            }
            if (formSubmitted) {
                displayBooks = (List<BookModel>) request.getAttribute("searchResults");
            }
            else {
                displayBooks = (List<BookModel>) session.getAttribute("allBooks");
            }
    
            if (displayBooks != null) {
                Iterator<BookModel> iterator = displayBooks.iterator();
                while (iterator.hasNext()) {
                    BookModel book = iterator.next();
                    
                   
            %>
            <tr>
                <td><%= book.getTitre() %></td>
                <td><%= book.getAuteur() %></td>
                <td><%= book.getGenre() %></td>
                <td><%= book.getNbExemplaire() %></td>
                <td>
                    
                        <button type="button" name="edit" value="<%= book.getTitre() %>" class="btn" onclick="showUpdateForm('<%= book.getTitre() %>')">Edit</button>
                    <button type="button" name="borrow" value="<%= book.getTitre() %>" class="btn" onclick="showBorrowForm('<%= book.getTitre() %>')">Borrow</button>
                </td>
                    
                       
                </td> 
            </tr>
            <% }
            }   %>
        </table>
    </div>
</body>
</html>
