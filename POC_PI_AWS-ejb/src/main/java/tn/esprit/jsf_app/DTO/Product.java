package tn.esprit.jsf_app.DTO;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	public int id;
	public String nom;
	public int prix;
	public String imageString;
	public String Categorie;
	public Byte[] imageByte;
	public int quantite;
	public String description;
	
	
	public Product() {
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	public Byte[] getImageByte() {
		return imageByte;
	}
	public void setImageByte(Byte[] imageByte) {
		this.imageByte = imageByte;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getCategory() {
		return Categorie;
	}
	public void setCategory(String category) {
		this.Categorie = category;
	}
	public Product(int id, String nom, int prix, String img) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.imageString = img;
	}
	public Product(String nom, int prix, String img, String category, Byte[] imageByte, int quantite) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.imageString = img;
		this.Categorie = category;
		this.imageByte = imageByte;
		this.quantite = quantite;
	}
	public Product(String nom, int prix, String category, int quantite) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.Categorie = category;
		this.quantite = quantite;
	}
	public Product(String nom, int prix, String category, int quantite, String description) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.Categorie = category;
		this.quantite = quantite;
		this.description = description;
	}
	public Product(String nom, int prix, String imageString, String category, int quantite, String description) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.imageString = imageString;
		this.Categorie = category;
		this.quantite = quantite;
		this.description = description;
	}
	public Product(int id, int quantite) {
		super();
		this.id = id;
		this.quantite = quantite;
	}
	public Product(int id, String nom, int prix, String imageString, String categorie, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.imageString = imageString;
		Categorie = categorie;
		this.description = description;
	}
	

	
	
	
	

}

