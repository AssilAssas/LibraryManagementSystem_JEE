package metier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.catalina.User;

import Connection.DatabaseManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.entities.UserModel;
public class UserMetier {
	
	public static boolean validateUser(String email, String password) {
	    // Vérifie si email est null ou vide
	    if (email == null || email.isEmpty()) {
	        return false;
	    }

	    return isValidEmail(email) && isValidPassword(password);
	}

	private static boolean isValidEmail(String email) {
	    // Vérifie si email est null
	    if (email == null) {
	        return false;
	    }

	    // Utilisation d'une expression régulière simple pour valider l'email
	    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    Pattern pattern = Pattern.compile(emailRegex);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}

	private static boolean isValidPassword(String password) {
	    // Vérifie si la longueur du mot de passe est supérieure à 8 caractères
	    return password != null && password.length() > 8;
	}

	    public static boolean authenticateUser(UserModel user) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	       

	        try {
	            // Obtenir une connexion à partir de DatabaseManager
	            connection = DatabaseManager.getConnection();

	            String query = "SELECT * FROM user WHERE `E-mail` = ? AND `MotDePasse` = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, user.getEmail());
	            preparedStatement.setString(2, user.getMotDePasse());
	            resultSet = preparedStatement.executeQuery();

	            return resultSet.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            try {
	                if (resultSet != null) resultSet.close();
	                if (preparedStatement != null) preparedStatement.close();
	                // Remettre la connexion dans le pool au lieu de la fermer explicitement
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    public static boolean registerUser(UserModel user) {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connection = DatabaseManager.getConnection();
	            String query = "INSERT INTO user (`Nom`,`Prenom`,`E-mail`,`MotDePasse`) VALUES (?, ?, ?, ?)";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, user.getNom());
	            preparedStatement.setString(2, user.getPrenom());
	            preparedStatement.setString(3, user.getEmail());
	            preparedStatement.setString(4, user.getMotDePasse());

	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0; // Registration is successful if at least one row is affected
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            // Close resources (ResultSet, PreparedStatement, Connection)
	            try {
	                if (preparedStatement != null) preparedStatement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    public static int getUserIdByEmailAndPassword(String email, String password) {
	        int userId = -1; // Default value indicating user not found or error
	        
	        try (Connection connection = DatabaseManager.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "SELECT Id FROM user WHERE `E-mail`=? AND `MotDePasse`=?"
	             )) {
	            preparedStatement.setString(1, email);
	            preparedStatement.setString(2, password);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    userId = resultSet.getInt("Id");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return userId;
	    }
	    public  String getUserNameById(int id) {
		    String name = "";

		    try (Connection connection = DatabaseManager.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement("SELECT `Prenom` FROM user WHERE `Id`=?")) {

		        preparedStatement.setInt(1, id); // Set the parameter value

		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            if (resultSet.next()) {
		                name = resultSet.getString("Prenom");
		            }
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		        // Handle the exception appropriately (log it, throw a custom exception, etc.)
		    }

		    return name;
		}
	    
	}



