package application;

public class Model_Facture_Page {
	private int id;
	private String n_client;
	private String date;
	private String contact_client;
	private int telephone;
	private int prix_total;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getN_client() {
		return n_client;
	}
	public Model_Facture_Page() {
		
		
	}
	
	public Model_Facture_Page(int id, String n_client, String date, String contact_client, int telephone,
			int prix_total) {
		super();
		this.id = id;
		this.n_client = n_client;
		this.date = date;
		this.contact_client = contact_client;
		this.telephone = telephone;
		this.prix_total = prix_total;
	}
	public void setN_client(String n_client) {
		this.n_client = n_client;
	}
	public String getContact_client() {
		return contact_client;
	}
	public void setContact_client(String contact_client) {
		this.contact_client = contact_client;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public int getPrix_total() {
		return prix_total;
	}
	public void setPrix_total(int prix_total) {
		this.prix_total = prix_total;
	}
	

}
