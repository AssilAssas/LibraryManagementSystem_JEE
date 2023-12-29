package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.DatabaseManager;
import metier.entities.BookModel;
import metier.entities.EmpruntModel;

public class BookMetier {
	public BookMetier() {}

    public static boolean AddBook(BookModel book) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO book (`Titre`, `Auteur`, `Genre`, `nbExemplaire`) VALUES (?, ?, ?, ?)"
             )) {
            preparedStatement.setString(1, book.getTitre());
            preparedStatement.setString(2, book.getAuteur());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setInt(4, book.getNbExemplaire());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public  String getBookTitleById(int bookId) {
        String title="";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT `Titre` FROM book WHERE `Id`=?")) {

            preparedStatement.setInt(1, bookId); // Set the parameter value

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    title = resultSet.getString("Titre");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately (log it, throw a custom exception, etc.)
        }

        return title;
    }


    public static boolean UpdateBook(BookModel updatedBook) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE book SET Titre=?, Auteur=?, Genre=?, nbExemplaire=? WHERE Id=?"
             )) {
            preparedStatement.setString(1, updatedBook.getTitre());
            preparedStatement.setString(2, updatedBook.getAuteur());
            preparedStatement.setString(3, updatedBook.getGenre());
            preparedStatement.setInt(4, updatedBook.getNbExemplaire());
            preparedStatement.setInt(5, updatedBook.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkIfTitleExists(String bookTitle) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM book WHERE `Titre` = ?"
             )) {
            preparedStatement.setString(1, bookTitle);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<BookModel> performSearch(String searchQuery) {
        List<BookModel> searchResults = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM book WHERE Titre LIKE ?"
             )) {
            preparedStatement.setString(1, "%" + searchQuery + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    BookModel book = new BookModel();
                    book.setId(resultSet.getInt("id"));
                    book.setTitre(resultSet.getString("Titre"));
                    book.setAuteur(resultSet.getString("Auteur"));
                    book.setGenre(resultSet.getString("Genre"));
                    book.setNbExemplaire(resultSet.getInt("nbExemplaire"));

                    searchResults.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }
    public BookModel getBookByTitle(String title) {
        BookModel book = new BookModel();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM book WHERE Titre LIKE ?"
        )) {
            preparedStatement.setString(1, title);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {  // Move the cursor to the first row
                    book.setId(resultSet.getInt("id"));
                    book.setTitre(resultSet.getString("Titre"));
                    book.setAuteur(resultSet.getString("Auteur"));
                    book.setGenre(resultSet.getString("Genre"));
                    book.setNbExemplaire(resultSet.getInt("nbExemplaire"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }


    public List<BookModel> getAllBooks() {
        List<BookModel> books = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT `Titre`, `Auteur`, `Genre`, `nbExemplaire` FROM book"
             );
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                BookModel book = new BookModel();
             //   book.setTitre(resultSet.getString("Id"));
                book.setTitre(resultSet.getString("Titre"));
                book.setAuteur(resultSet.getString("Auteur"));
                book.setGenre(resultSet.getString("Genre"));
                book.setNbExemplaire(resultSet.getInt("nbExemplaire"));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
    public static BookModel getBookDetailsById(String bookId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BookModel book = null;

        try {
            
            connection = DatabaseManager.getConnection();

            String sql = "SELECT * FROM book WHERE Id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookId);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            if (resultSet.next()) {
                book = new BookModel();
                book.setId(resultSet.getInt("Id"));
                book.setTitre(resultSet.getString("Titre"));
                book.setAuteur(resultSet.getString("Auteur"));
                book.setGenre(resultSet.getString("Genre"));
                book.setNbExemplaire(resultSet.getInt("nbExemplaire"));
                // Add more fields as needed
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Close the resources in a finally block
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }

        return book;
    }
    public static boolean UpdateBookByNbExemp(BookModel updatedBook) {
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE book SET  nbExemplaire=? WHERE Id=?"
             )) {
            
            preparedStatement.setInt(1, updatedBook.getNbExemplaire());
            preparedStatement.setInt(2, updatedBook.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
   public boolean updateNbExemp(BookModel book,EmpruntModel emprunt) {
	   
	   int newNbExemp = book.getNbExemplaire()- emprunt.getNbEmpruntes();
	   book.setNbExemplaire(newNbExemp);
	   if(UpdateBookByNbExemp(book)) {
		   return true;
	   }
	   
	   return false;
   }

}
