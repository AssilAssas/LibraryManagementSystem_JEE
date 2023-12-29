package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.UserMetier;
import metier.entities.UserModel;
import Connection.DatabaseManager;

import java.io.IOException;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String nom = request.getParameter("Nom");
		    String Prenom = request.getParameter("Prenom");
	        String email = request.getParameter("E-mail");
	        String password = request.getParameter("MotDePasse");
	        String confirmPassword = request.getParameter("CNF_MDP");
	        UserModel model = new UserModel();
            model.setPrenom(Prenom);
            model.setNom(nom);
            model.setEmail(email);
            model.setMotDePasse(password);
	        // Perform validation checks (e.g., password match)
	        if (password != null && !password.equals(confirmPassword)) {
	            // Passwords do not match, handle the error
	            response.sendRedirect("register-error-p.jsp"); // Redirect to an error page
	            return;
	        }

	        // Call a method to register the user in the database
	        boolean registrationSuccess = UserMetier.registerUser(model);

	        if (registrationSuccess) {
	            // Registration successful, redirect to a success page
	            response.sendRedirect("Login.jsp");
	        } else {
	            // Registration failed, redirect to an error page
	            response.sendRedirect("register-error.jsp");
	        }
	    }
	}


