package tn.esprit.jsf_app.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;
	int id;
	Date dateAchat ;
	int prixTotal ;
	List<CartLine> cartLines ;
	
	
	
	public Cart(int id, Date dateAchat, int prixTotal, List<CartLine> cartLines) {
		this.id = id;
		this.dateAchat = dateAchat;
		this.prixTotal = prixTotal;
		this.cartLines = cartLines;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateAchat() {
		return dateAchat;
	}
	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
	public int getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(int prixTotal) {
		this.prixTotal = prixTotal;
	}
	public List<CartLine> getCartLines() {
		return cartLines;
	}
	public void setCartLines(List<CartLine> cartLines) {
		this.cartLines = cartLines;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", dateAchat=" + dateAchat + ", prixTotal=" + prixTotal + ", cartLines=" + cartLines
				+ "]";
	}

	
	
	

}
