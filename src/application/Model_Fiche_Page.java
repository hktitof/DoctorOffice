package application;

public class Model_Fiche_Page {
	
	private int id;
	private String prenom;
	private String nom;
	private String date_n;
	private String cin;
	private String profession;
	private String mutuelle;
	private String date_cre;
	private String comm;
	
	public Model_Fiche_Page() {
		// TODO Auto-generated constructor stub
	}
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
	public String getDate_n() {
		return date_n;
	}
	public void setDate_n(String date_n) {
		this.date_n = date_n;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getMutuelle() {
		return mutuelle;
	}
	public void setMutuelle(String mutuelle) {
		this.mutuelle = mutuelle;
	}
	public String getDate_cre() {
		return date_cre;
	}
	public void setDate_cre(String date_cre) {
		this.date_cre = date_cre;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	public Model_Fiche_Page(int id, String prenom, String nom, String date_n, String cin, String profession,
			String mutuelle, String date_cre, String comm) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.date_n = date_n;
		this.cin = cin;
		this.profession = profession;
		this.mutuelle = mutuelle;
		this.date_cre = date_cre;
		this.comm = comm;
	}

}
