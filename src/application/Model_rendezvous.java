package application;



public class Model_rendezvous {
	
	private int id;
	private String prenom;
	private String nom;
	private int telephone;
	private String date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Model_rendezvous(int id, String prenom, String nom, int telephone, String date) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.date = date;
	}
	public Model_rendezvous() {
		// TODO Auto-generated constructor stub
	}

	

}
