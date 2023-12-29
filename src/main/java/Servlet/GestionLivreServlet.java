package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import metier.BookMetier;
import metier.EmpruntMetier;
import metier.entities.BookModel;
import metier.entities.EmpruntModel;

public class GestionLivreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GestionLivreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean userIsLoggedIn = checkUserLoggedIn(request);

        if (userIsLoggedIn) {
            try {
                BookMetier bookMetier = new BookMetier();
                List<BookModel> allBooks = bookMetier.getAllBooks();
                System.out.println("Number of books fetched: " + allBooks.size());
                
                // Set the list of books in the session attribute
                HttpSession session = request.getSession();
                session.setAttribute("allBooks", allBooks);
               // request.setAttribute("allBooks", allBooks);

                // Forward the request to your JSP page
               request.getRequestDispatcher("home.jsp").forward(request, response);
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
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("AddBook.jsp");
		String action = request.getParameter("action");
		if ("add".equals(action)) {
		int nbexp=0;
		    String title = request.getParameter("Titre");
		    String auteur = request.getParameter("Auteur");
		    String genre = request.getParameter("Genre");
		    try {
		        nbexp = Integer.parseInt(request.getParameter("nbExemplaire"));
		    } catch (NumberFormatException e) {
		        // Handle the case where nbExemplaire is not a valid integer
		        e.printStackTrace(); // or log the exception
		        response.sendRedirect("register-error-M.jsp");
		        return;
		    }

		    BookModel model = new BookModel();
		    model.setTitre(title);
		    model.setAuteur(auteur);
		    model.setGenre(genre);
		    model.setNbExemplaire(nbexp);
            
		    
		    boolean checkTitle = BookMetier.checkIfTitleExists(title);
		    System.out.println("checkTitle: " + checkTitle);
		    if(!checkTitle) {
		    	boolean bookAdded = BookMetier.AddBook(model);
		    if (bookAdded) {
		    	System.out.println("bookAdded: " + bookAdded);
		        // added successfully, redirect to a success page
		    //	 request.getRequestDispatcher("home.jsp").forward(request, response);
		        doGet(request, response);
		    } else {
		        // failed, redirect to an error page
		        response.sendRedirect("register-error.jsp");
		    }
		    } else {
		        // failed, redirect to an error page
		        response.sendRedirect("register-error-exist.jsp");
		    }
		    
			/*
			 * String searchQuery = request.getParameter("search"); List<BookModel>
			 * searchResults = new BookMetier().performSearch(searchQuery);
			 * 
			 * // Set search results as a request attribute
			 * request.setAttribute("searchResults", searchResults);
			 * 
			 * // Forward to the same JSP page
			 * request.getRequestDispatcher("home.jsp").forward(request, response);
			 */
		} else if ("search".equals(action)) {
		    String searchQuery = request.getParameter("search");
		    List<BookModel> searchResults = new BookMetier().performSearch(searchQuery);
		    request.setAttribute("searchResults", searchResults);
		    request.getRequestDispatcher("home.jsp").forward(request, response);
	

        } 
		else if ("update".equals(action)) {
			int nbexp=0,id=0;
		    String title = request.getParameter("Titre");
		    String auteur = request.getParameter("Auteur");
		    String genre = request.getParameter("Genre");
		    try {
		        nbexp = Integer.parseInt(request.getParameter("nbExemplaire"));
		        id = Integer.parseInt(request.getParameter("Id"));
		    } catch (NumberFormatException e) {
		        // Handle the case where nbExemplaire is not a valid integer
		        e.printStackTrace(); // or log the exception
		        response.sendRedirect("register-error-M.jsp");
		        return;
		    }

		    BookModel model = new BookModel();
		    model.setId(id);
		    model.setTitre(title);
		    model.setAuteur(auteur);
		    model.setGenre(genre);
		    model.setNbExemplaire(nbexp); 
		    boolean bookUpdated = BookMetier.UpdateBook(model);	
		    if (bookUpdated) {
		    	System.out.println(model.getNbExemplaire());
		        // added successfully, redirect to a success page
		    	// request.getRequestDispatcher("home.jsp").forward(request, response);
		        doGet(request, response);
		    } else {
		        // failed, redirect to an error page
		        response.sendRedirect("register-error.jsp");
		    }
		
		    
	        
			
		} 
		else if ("borrow".equals(action)) {
		    int nbexp = 0, id = 0, nbJour = 0, nbExemplaire2 = 0;
		    String titre = request.getParameter("Titre");
		    String auteur = request.getParameter("Auteur");
		    
		    try {
		        nbexp = Integer.parseInt(request.getParameter("nbExemplaire"));
		        System.out.println(nbexp);
		        
		        id = Integer.parseInt(request.getParameter("Id"));
		        System.out.println(id);
		        nbJour = Integer.parseInt(request.getParameter("nbJour"));
		        System.out.println(nbJour);
		        nbExemplaire2 = Integer.parseInt(request.getParameter("nbExemplaire2"));
		        System.out.println(nbExemplaire2);
		    } catch (NumberFormatException e) {
		        e.printStackTrace();
		        response.sendRedirect("parsing-error.jsp");
		        return;
		    }

		    BookModel book = new BookModel();
		    book.setId(id);
		    book.setAuteur(auteur);
		    book.setNbExemplaire(nbexp);
		    book.setTitre(titre);
		     
		    HttpSession session = request.getSession();
		    
		    String idUserString= (String) session.getAttribute("idUser") ;
		    int idUser = Integer.parseInt(idUserString);
		    
		    
		    

		    EmpruntModel emprunt = new EmpruntModel();
		    emprunt.setIdUser(idUser);
		    emprunt.setNbEmpruntes(nbExemplaire2);
		    emprunt.setIdBook(id);
		    emprunt.setNbJours(nbJour);

		    BookMetier bookMetier = new BookMetier();
		    EmpruntMetier empruntMetier = new EmpruntMetier();

		    // Add the emprunt and check if it was successful
		    boolean empruntAdded = empruntMetier.addEmprunt(emprunt);

		    if (empruntAdded) {
		        // Update the book and check if it was successful
		        boolean bookUpdated = bookMetier.updateNbExemp(book, emprunt);

		        if (bookUpdated) {
		            // Both emprunt and book update were successful
		            // You can add further logic or redirect the user to a success page
		          //  response.sendRedirect("home.jsp");
		        	doGet(request, response);
		        } else {
		            // Book update failed
		            response.sendRedirect("book-update-failed.jsp");
		        }
		    } else {
		        // Emprunt add failed
		        response.sendRedirect("emprunt-add-failed.jsp");
		    }
		}

		
	
	
	
	
	
	}
	






}
	


