package tn.esprit.jsf_app.DTO;

import java.io.Serializable;
import java.util.Date;

public class CartLine implements Serializable {
	private static final long serialVersionUID = 1L;
	public int id;
	public int cartId ;
	public int quantiteChoisie ;
	public int prixTotal ;
	public Date dateAjout ;
	public Product myProduct ;
	public String nomProd;
	public int prixunitaire;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getQuantiteChoisie() {
		return quantiteChoisie;
	}
	public void setQuantiteChoisie(int quantiteChoisie) {
		this.quantiteChoisie = quantiteChoisie;
	}
	public int getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(int prixTotal) {
		this.prixTotal = prixTotal;
	}
	public Date getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	public Product getMyProduct() {
		return myProduct;
	}
	public void setMyProduct(Product myProduct) {
		this.myProduct = myProduct;
	}
	
	public String getNomProd() {
		return nomProd;
	}
	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
	}
	
	public int getPrixunitaire() {
		return prixunitaire;
	}
	public void setPrixunitaire(int prixunitaire) {
		this.prixunitaire = prixunitaire;
	}
	public CartLine(int id, int cartId, int quantiteChoisie, int prixTotal, Date dateAjout, Product myProduct) {
		this.id = id;
		this.cartId = cartId;
		this.quantiteChoisie = quantiteChoisie;
		this.prixTotal = prixTotal;
		this.dateAjout = dateAjout;
		this.myProduct = myProduct;
	}
	public CartLine(int id, int cartId, int quantiteChoisie, int prixTotal, Product myProduct) {
		this.id = id;
		this.cartId = cartId;
		this.quantiteChoisie = quantiteChoisie;
		this.prixTotal = prixTotal;
		this.myProduct = myProduct;
	}
	public CartLine() {
		super();
	}
	public CartLine(int id, int quantiteChoisie) {
		super();
		this.id = id;
		this.quantiteChoisie = quantiteChoisie;
	}

	
	

}