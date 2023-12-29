package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.BookMetier;
import metier.EmpruntMetier;
import metier.entities.BookModel;
import metier.entities.EmpruntModel;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class HistoryServlet
 */
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  boolean userIsLoggedIn = checkUserLoggedIn(request);
          System.out.println(userIsLoggedIn);
	        if (userIsLoggedIn) {
	            try {
	                
	                List<EmpruntModel> allEmprunts = EmpruntMetier.getAllEmprunts();
	                System.out.println("Number of emprunts fetched: " + allEmprunts.size());
	                
	                // Set the list of books in the session attribute
	                HttpSession session = request.getSession();
	                session.setAttribute("allEmprunts", allEmprunts);
	               // request.setAttribute("allBooks", allBooks);

	                // Forward the request to your JSP page
	               request.getRequestDispatcher("history.jsp").forward(request, response);
	            } catch (Exception e) {
	                e.printStackTrace();
	                response.sendRedirect("error.jsp"); // Redirect to an error page
	            }
	        } else {
	            // Redirect to login page or handle authentication
	            response.sendRedirect("login.jsp");
	        }
	}
	public boolean checkUserLoggedIn(HttpServletRequest request) { 
		  HttpSession session = request.getSession(false);
		  // don't create a new session if none exists
		  session.setAttribute("userIsLoggedIn", true);
		  
		  return session != null && session.getAttribute("userIsLoggedIn") != null &&
		  (Boolean) session.getAttribute("userIsLoggedIn"); }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
