package tn.esprit.jsf_app.services;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.jsf_app.DTO.*;
import tn.esprit.jsf_app.interfaces.*;

@Stateful
@LocalBean
public class ProductCartService implements ProductCartServiceRemote, ProductCartServiceLocal {

	public String GlobalEndPoint = "localhost:44326";

	@Override
	public List<Product> GetAllProduct() {

		Client client = ClientBuilder.newClient();
		WebTarget web = client.target("http://" + GlobalEndPoint + "/Api/GetAllProduct");
		Response response = web.request().get();
		String result = response.readEntity(String.class);
		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();

		List<Product> productList = new ArrayList<Product>();
		for (int i = 0; i < object.size(); i++) {

			Product myProduct = new Product();

			myProduct.setId(object.getJsonObject(i).getInt("id"));
			myProduct.setNom(object.getJsonObject(i).getString("nom"));
			myProduct.setImageString(object.getJsonObject(i).getString("imageString"));
			myProduct.setPrix(object.getJsonObject(i).getInt("prix"));
			myProduct.setCategory(object.getJsonObject(i).getString("Categorie"));
			myProduct.setDescription(object.getJsonObject(i).getString("description"));

			productList.add(myProduct);

		}
		Collections.reverse(productList);
		return productList;

	}

	@Override
	public List<Cart> GetAllCartByUserId(int userId) {

		ArrayList<Cart> cartList = new ArrayList<Cart>();
		Client client = ClientBuilder.newClient();
		WebTarget web = client.target("http://" + GlobalEndPoint + "/Api/GetAllCartByUserId/" + userId);
		Response response = web.request().get();
		String result = response.readEntity(String.class);
		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();

		for (int i = 0; i < object.size(); i++) {
			if (object.getJsonObject(i).getBoolean("status") == false) {
				// recuperation date
				Date cartDate = null;
				try {
					String jsonDate = object.getJsonObject(i).getString("dateAchat");
					SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
					cartDate = formatDate.parse(jsonDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				JsonArray cartLineJsonArray = object.getJsonObject(i).getJsonArray("CartLines");
				ArrayList<CartLine> cartLineList = new ArrayList<CartLine>();
				for (int j = 0; j < cartLineJsonArray.size(); j++) {

					CartLine cartLine = new CartLine(cartLineJsonArray.getJsonObject(j).getInt("id"),
							cartLineJsonArray.getJsonObject(j).getInt("CartId"),
							cartLineJsonArray.getJsonObject(j).getInt("quantiteChoisie"),
							cartLineJsonArray.getJsonObject(j).getInt("prixTotal"), cartDate,
							new Product(cartLineJsonArray.getJsonObject(j).getJsonObject("myProduct").getInt("id"),
									cartLineJsonArray.getJsonObject(j).getJsonObject("myProduct").getString("nom"),
									cartLineJsonArray.getJsonObject(j).getJsonObject("myProduct").getInt("prix"),
									cartLineJsonArray.getJsonObject(j).getJsonObject("myProduct")
											.getString("imageString")));
					cartLineList.add(cartLine);
					// cartTotalPrice +=
					// cartLineJsonArray.getJsonObject(j).getInt("totalCartLinePrice");
				}
				Cart cart = new Cart(object.getJsonObject(i).getInt("id"), cartDate,
						object.getJsonObject(i).getInt("prixTotal"), cartLineList);
				cartList.add(cart);
			}
		}
		Collections.reverse(cartList);
		return cartList;
	}

	public List<CartLine> MyCart() {

		ArrayList<CartLine> cartList = new ArrayList<CartLine>();
		Client client = ClientBuilder.newClient();
		WebTarget web = client.target("http://" + GlobalEndPoint + "/Api/MyCart");
		Response response = web.request().get();
		String result = response.readEntity(String.class);
		JsonReader jsonReader = Json.createReader(new StringReader(result));
		JsonArray object = jsonReader.readArray();

		for (int i = 0; i < object.size(); i++) {

			CartLine m = new CartLine();
			m.setId(object.getJsonObject(i).getInt("id"));
			m.setMyProduct(new Product(object.getJsonObject(i).getJsonObject("myProduct").getInt("id"),
					object.getJsonObject(i).getJsonObject("myProduct").getString("nom"),
					object.getJsonObject(i).getJsonObject("myProduct").getInt("prix"),
					object.getJsonObject(i).getJsonObject("myProduct").getString("imageString"),
					object.getJsonObject(i).getJsonObject("myProduct").getString("Categorie"),
					object.getJsonObject(i).getJsonObject("myProduct").getString("description")));

			m.setCartId(object.getJsonObject(i).getInt("CartId"));
			m.setNomProd(object.getJsonObject(i).getJsonObject("myProduct").getString("nom"));
			m.setPrixTotal(object.getJsonObject(i).getInt("prixTotal"));
			m.setQuantiteChoisie(object.getJsonObject(i).getInt("quantiteChoisie"));
			m.setPrixunitaire(object.getJsonObject(i).getJsonObject("myProduct").getInt("prix"));
			cartList.add(m);
		}

		Collections.reverse(cartList);
		return cartList;
	}

	@Override
	public void DeleteCart(int cartId) {
		Client client = ClientBuilder.newClient();
		WebTarget web = client.target("http://" + GlobalEndPoint + "/Api/DeleteCartById/" + cartId);
		Response response = web.request().delete();
		response.close();
	}

	public void Create(Product p) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://" + GlobalEndPoint + "/Api/Create");
		WebTarget hello = target.path("");
		Response response = hello.request().post(Entity.entity(p, MediaType.APPLICATION_JSON));

		String result = response.readEntity(String.class);
		System.out.println(result);

		response.close();
	}

	public void AddProductToMyCart(int idp, Product p) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://" + GlobalEndPoint + "/Api/AddProductToMyCart?idp=" + idp);
		WebTarget hello = target.path("");
		Response response = hello.request().post(Entity.entity(p, MediaType.APPLICATION_JSON));

		String result = response.readEntity(String.class);
		System.out.println(result);

		response.close();
	}

	@Override
	public List<Cart> searchProductByName(String name, List<Cart> cartList) {
		List<Cart> cartLitSearched = new ArrayList<Cart>();
		for (Cart cart : cartList) {
			List<CartLine> cartLineList = cart.getCartLines();
			for (CartLine cartLine : cartLineList) {
				System.out.println(cartLine.getMyProduct().getNom());
				if (cartLine.getMyProduct().getNom().equals(name)) {
					cartLitSearched.add(cart);
					break;
				}

			}
		}
		return cartLitSearched;

	}

	@Override
	public List<Cart> SearchByPrice(String prix1, String prix2, List<Cart> cartList) {
		List<Cart> cartLitSearched = new ArrayList<Cart>();
		int prixMin = Integer.parseInt(prix1);
		int prixMax = Integer.parseInt(prix2);
		for (Cart cart : cartList) {
			List<CartLine> cartLineList = cart.getCartLines();
			if ((cart.getPrixTotal() <= prixMax) && (cart.getPrixTotal() >= prixMin)) {
				cartLitSearched.add(cart);
			}
		}
		return cartLitSearched;

	}
	public void ConfirmPurchase() {
		Client client = ClientBuilder.newClient();

		WebTarget web = client.target("http://" + GlobalEndPoint + "/Api/ConfirmPurchase");

		Response response = web.request().get();

	}
	public void Update(int idc, CartLine k) {
		CartLine km = new CartLine();

		km.setQuantiteChoisie(k.getQuantiteChoisie());

		System.out.println("iddddddddd" + idc);

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://" + GlobalEndPoint + "/Api/EditCartLine?idc=" + idc);
		Response response = target.request().put(Entity.entity(k, MediaType.APPLICATION_JSON));
		System.out.println(response);

	}

}