package fr.emploi.model;

public class Image {

	private int id;
	private String nom;
	private String url1;
	private String url2;
	
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Image(int id, String nom, String url1, String url2) {
		super();
		this.id = id;
		this.nom = nom;
		this.url1 = url1;
		this.url2 = url2;
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

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", nom=" + nom + ", url1=" + url1 + ", url2=" + url2 + "]";
	}
	
}
