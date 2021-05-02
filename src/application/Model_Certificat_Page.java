package application;

public class Model_Certificat_Page {
	private int id;
	private String nom;
	private String date_naiss;
	private String etabli_a;
	private String date;
	
	public Model_Certificat_Page() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDate_naiss() {
		return date_naiss;
	}

	public void setDate_naiss(String date_naiss) {
		this.date_naiss = date_naiss;
	}

	public String getEtabli_a() {
		return etabli_a;
	}

	public void setEtabli_a(String etabli_a) {
		this.etabli_a = etabli_a;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Model_Certificat_Page(int id, String nom, String date_naiss, String etabli_a, String date) {
		super();
		this.id = id;
		this.nom = nom;
		this.date_naiss = date_naiss;
		this.etabli_a = etabli_a;
		this.date = date;
	}
	
	

}
