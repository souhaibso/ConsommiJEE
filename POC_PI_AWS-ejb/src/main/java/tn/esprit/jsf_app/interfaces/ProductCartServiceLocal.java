package tn.esprit.jsf_app.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.jsf_app.DTO.*;

@Local

public interface ProductCartServiceLocal {
	
	 List<Product> GetAllProduct();
	 List<Cart> GetAllCartByUserId(int userId) ;
	 void DeleteCart(int cartId) ;
	 List<Cart> searchProductByName(String name, List<Cart> cartList);
	 List<Cart> SearchByPrice(String prix1 , String prix2 , List<Cart> cartList) ;

}
