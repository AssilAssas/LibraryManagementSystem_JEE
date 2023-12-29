package metier;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import Connection.DatabaseManager;
import metier.entities.EmpruntModel;


public class EmpruntMetier {
	
	
	public EmpruntMetier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static boolean addEmprunt(EmpruntModel emprunt) {
	    try (Connection connection = DatabaseManager.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "INSERT INTO emprunt (`IdUser`, `IdBook`, `nb_empruntés`, `Date_Emprunt`, `Date_Retour`, `Statue_Actuel_Emp`) " +
	                         "VALUES (?, ?, ?, ?, ?, ?)"
	         )) {
	        preparedStatement.setInt(1, emprunt.getIdUser());
	        preparedStatement.setInt(2, emprunt.getIdBook());
	        preparedStatement.setInt(3, emprunt.getNbEmpruntes());

	        // Set Date_Emprunt to the current system date
	        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
	        preparedStatement.setDate(4, currentDate);

	        // Set Date_Retour to the system date plus the number of days
	        java.util.Calendar calendar = java.util.Calendar.getInstance();
	        calendar.setTime(currentDate);
	        calendar.add(java.util.Calendar.DAY_OF_YEAR, emprunt.getNbJours());
	        java.sql.Date returnDate = new java.sql.Date(calendar.getTimeInMillis());
	        preparedStatement.setDate(5, returnDate);

	        preparedStatement.setString(6,"Non Disponible");

	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public static List<EmpruntModel> getAllEmprunts() {
	    List<EmpruntModel> allEmprunts = new ArrayList<>();
	    String title="",Name="";
        UserMetier usermetier = new UserMetier();
        BookMetier bookmetier = new BookMetier();
	    try (Connection connection = DatabaseManager.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM emprunt");
	         ResultSet resultSet = preparedStatement.executeQuery()) {
          
	        while (resultSet.next()) {
	        	title = bookmetier.getBookTitleById(resultSet.getInt("IdBook"));
	        	Name = usermetier.getUserNameById(resultSet.getInt("IdUser"));
	            EmpruntModel emprunt = new EmpruntModel();
	            emprunt.setUserName(Name);
	            emprunt.setBookTitle(title);
	            emprunt.setNbEmpruntes(resultSet.getInt("nb_empruntés"));
	            emprunt.setDateEmprunt(resultSet.getDate("Date_Emprunt"));
	            emprunt.setDateRetour(resultSet.getDate("Date_Retour"));
	            emprunt.setStatueActuelEmp(resultSet.getString("Statue_Actuel_Emp"));

	            allEmprunts.add(emprunt);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the exception appropriately (log it, throw a custom exception, etc.)
	    }

	    return allEmprunts;
	}
	public static String getUserNameById(int id) {
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


	
	
	
	

	//public static boolean update
	 
}
