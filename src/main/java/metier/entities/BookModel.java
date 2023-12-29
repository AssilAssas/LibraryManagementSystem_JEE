package metier.entities;

public class BookModel {
	private int Id ;
	private String Titre ;
	private String Auteur ;
	private String Genre ;
	
	private int nbExemplaire ;
	
	
	public BookModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookModel(int id, String titre, String auteur, String genre, int nbExemplaire) {
		super();
		Id = id;
		Titre = titre;
		Auteur = auteur;
		Genre = genre;
		this.nbExemplaire = nbExemplaire;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		Titre = titre;
	}
	public String getAuteur() {
		return Auteur;
	}
	public void setAuteur(String auteur) {
		Auteur = auteur;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	public int getNbExemplaire() {
		return nbExemplaire;
	}
	public void setNbExemplaire(int nbExemplaire) {
		this.nbExemplaire = nbExemplaire;
	}
	

}
