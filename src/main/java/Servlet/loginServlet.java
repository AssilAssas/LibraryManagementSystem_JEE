package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


import metier.UserMetier ;

import metier.entities.UserModel;


public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public boolean checkUserLoggedIn(HttpServletRequest request){    
   	 HttpSession session =request.getSession(false); // don't create a new session if none exists

        return session != null && session.getAttribute("userIsLoggedIn") != null &&
           (Boolean) session.getAttribute("userIsLoggedIn"); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  response.sendRedirect("Login.jsp"); } 
	       
	
	
     

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Retrieve user credentials from the request parameters
	    String email = request.getParameter("E-mail");
	    String password = request.getParameter("MotDePasse");
        
	    // Validate user credentials
	    UserModel model = new UserModel(email, password);
	    boolean isAuthenticated = UserMetier.authenticateUser(model);
	    
	   
	    if (isAuthenticated) {
	    	
	    	int userIdint = UserMetier.getUserIdByEmailAndPassword(email, password);
	    	
	        // Set UserModel object in session for further use
	        HttpSession session = request.getSession();
	        String userId = String.valueOf(userIdint);
	        session.setAttribute("UserModel", model);
	        session.setAttribute("idUser", userId);
	        System.out.println("this is the usr id : "+userId);
	        System.out.println(session.getAttribute("idUser"));

	        // Redirect to the home page
	        response.sendRedirect("GestionLivreServlet"); // Change to the appropriate URL mapping of GestionLivreServlet
	    } else {
	        // Redirect to the login page with an error message
	        response.sendRedirect("login-error.jsp");
	    }
	}

		    }


		    
		    
		    
	
		    

