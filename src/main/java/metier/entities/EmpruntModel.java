package metier.entities;

import java.util.Date;

public class EmpruntModel {
	 private int IdEmprunt;
	 private int IdUser;
	 private int IdBook;
	 private String UserName;
	 private String BookTitle;
	 private int NbEmpruntes;
	 private int NbJours;
	 private Date DateEmprunt;
	 private Date DateRetour;
	 private String StatueActuelEmp;
	 
	public EmpruntModel() {
		super();
	}
	
    
	public int getIdUser() {
		return IdUser;
	}


	public void setIdUser(int idUser) {
		IdUser = idUser;
	}


	public int getIdBook() {
		return IdBook;
	}


	public void setIdBook(int idBook) {
		IdBook = idBook;
	}


	public EmpruntModel(String userName, String bookTitle, int nbEmpruntes, int nbJours, Date dateEmprunt,
			Date dateRetour, String statueActuelEmp) {
		super();
		UserName = userName;
		BookTitle = bookTitle;
		NbEmpruntes = nbEmpruntes;
		NbJours = nbJours;
		DateEmprunt = dateEmprunt;
		DateRetour = dateRetour;
		StatueActuelEmp = statueActuelEmp;
	}


	public EmpruntModel(int idEmprunt, String userName, String bookTitle, int nbEmpruntes, int nbJours,
			Date dateEmprunt, Date dateRetour, String statueActuelEmp) {
		super();
		IdEmprunt = idEmprunt;
		UserName = userName;
		BookTitle = bookTitle;
		NbEmpruntes = nbEmpruntes;
		NbJours = nbJours;
		DateEmprunt = dateEmprunt;
		DateRetour = dateRetour;
		StatueActuelEmp = statueActuelEmp;
	}


	public String getUserName() {
		return UserName;
	}


	public void setUserName(String userName) {
		UserName = userName;
	}


	public String getBookTitle() {
		return BookTitle;
	}


	public void setBookTitle(String bookTitle) {
		BookTitle = bookTitle;
	}


	public int getIdEmprunt() {
		return IdEmprunt;
	}


	public void setIdEmprunt(int idEmprunt) {
		IdEmprunt = idEmprunt;
	}


	public int getNbJours() {
		return NbJours;
	}


	public void setNbJours(int nbJours) {
		NbJours = nbJours;
	}
	

	

	public int getNbEmpruntes() {
		return NbEmpruntes;
	}

	public void setNbEmpruntes(int nbEmpruntes) {
		NbEmpruntes = nbEmpruntes;
	}

	public Date getDateEmprunt() {
		return DateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		DateEmprunt = dateEmprunt;
	}

	public Date getDateRetour() {
		return DateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		DateRetour = dateRetour;
	}

	public String getStatueActuelEmp() {
		return StatueActuelEmp;
	}

	public void setStatueActuelEmp(String statueActuelEmp) {
		StatueActuelEmp = statueActuelEmp;
	}
	

	
	
	 
	
	 
	
	 
	 
	 

}
