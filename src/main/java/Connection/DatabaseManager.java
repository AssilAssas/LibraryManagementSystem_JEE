package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
		
	   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bibliotheque";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "";

	    // Load the JDBC driver only once
	    static {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            // Handle the exception appropriately
	        }
	    }

	    public static Connection getConnection() throws SQLException {
	        try {
	            return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Log the exception or handle it appropriately
	            throw e;
	        }}
	
    
}
