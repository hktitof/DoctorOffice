package application;

public class Model_Ordonnance_Page {
	private int id;
	private String nom;
	private String address;
	private String qualite;
	private String denomination;
	private String forme;
	private String posologie;
	private String mode_emploi;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Model_Ordonnance_Page() {
		
	}
	
	public Model_Ordonnance_Page(int id, String nom, String address, String qualite, String denomination, String forme,
			String posologie, String mode_emploi) {
		super();
		this.id = id;
		this.nom = nom;
		this.address = address;
		this.qualite = qualite;
		this.denomination = denomination;
		this.forme = forme;
		this.posologie = posologie;
		this.mode_emploi = mode_emploi;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQualite() {
		return qualite;
	}
	public void setQualite(String qualite) {
		this.qualite = qualite;
	}
	public String getDenomination() {
		return denomination;
	}
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	public String getForme() {
		return forme;
	}
	public void setForme(String forme) {
		this.forme = forme;
	}
	public String getPosologie() {
		return posologie;
	}
	public void setPosologie(String posologie) {
		this.posologie = posologie;
	}
	public String getMode_emploi() {
		return mode_emploi;
	}
	public void setMode_emploi(String mode_emploi) {
		this.mode_emploi = mode_emploi;
	}

}
