package metier.entities;

public class UserModel {
private int Id;
private String Nom;
private String Prenom;
private String Email;
private String MotDePasse;


public UserModel() {
	super();
	// TODO Auto-generated constructor stub
}

public UserModel(int id,String nom, String prenom, String email, String motDePasse) {
	super();
	Id = id;
	Nom = nom;
	Prenom = prenom;
	Email = email;
	MotDePasse = motDePasse;
	
}

public int getId() {
	return Id;
}

public void setId(int id) {
	Id = id;
}

public String getNom() {
	return Nom;
}
public void setNom(String nom) {
	Nom = nom;
}
public String getPrenom() {
	return Prenom;
}
public void setPrenom(String prenom) {
	Prenom = prenom;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getMotDePasse() {
	return MotDePasse;
}
public void setMotDePasse(String motDePasse) {
	MotDePasse = motDePasse;
}

public UserModel(String email, String motDePasse) {
	super();
	Email = email;
	MotDePasse = motDePasse;
}



}
