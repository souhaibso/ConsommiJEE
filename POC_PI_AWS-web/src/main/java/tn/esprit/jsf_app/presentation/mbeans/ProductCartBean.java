package tn.esprit.jsf_app.presentation.mbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import tn.esprit.jsf_app.DTO.*;
import tn.esprit.jsf_app.services.*;

@ManagedBean(name = "productCartBean")
@SessionScoped
public class ProductCartBean implements Serializable {

	int userId = 2;

	@EJB
	ProductCartService productService;

	@PostConstruct
	public void init() {
	}

	List<Product> listAllProduct;

	public List<Product> listOfAllProduct() {
		listAllProduct = productService.GetAllProduct();
		return listAllProduct;
	}

	List<Cart> listAllCart;

	public List<Cart> listOfAllCart() {
		listAllCart = productService.GetAllCartByUserId(1);
		return listAllCart;
	}

	public Cart cartFromView;

	public void setCartFromView(Cart cartFromView) {
		this.cartFromView = cartFromView;
	}

	public void reload() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}

	public void deleteCart() throws IOException {
		productService.DeleteCart(cartFromView.getId());
		reload();
	}

	String prix1, prix2, keyword;
	private int id;
	private String nom;
	private int prix;
	private  String imageString;
	private String category;
	private Byte[] imageByte;
	private int quantite;
	public String description;
	private static String img1;
	

	public static String getImg1() {
		return img1;
	}

	public static void setImg1(String img1) {
		ProductCartBean.img1 = img1;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public  String getImageString() {
		return imageString;
	}

	public  void setImageString(String imageString) {
		this.imageString = imageString;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getPrix1() {
		return prix1;
	}

	public void setPrix1(String prix1) {
		this.prix1 = prix1;
	}

	public String getPrix2() {
		return prix2;
	}

	public void setPrix2(String prix2) {
		this.prix2 = prix2;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	List<Cart> listAllCartByUserId;

	public List<Cart> Search() throws ParseException {

		if (((null == prix1 || "".equals(prix1)) && (null == prix2 || "".equals(prix2)))
				&& (null == keyword || "".equals(keyword))) {
			listAllCartByUserId = productService.GetAllCartByUserId(1);
			return listAllCartByUserId;
		} else if (null == keyword || "".equals(keyword)) {
			listAllCartByUserId = productService.SearchByPrice(prix1, prix2, productService.GetAllCartByUserId(1));
			return listAllCartByUserId;

		} else if ((null == prix1 || "".equals(prix1)) || (null == prix2 || "".equals(prix2))) {
			listAllCartByUserId = productService.searchProductByName(keyword, productService.GetAllCartByUserId(1));
			return listAllCartByUserId;
		} else {
			listAllCartByUserId = productService.searchProductByName(keyword,
					productService.SearchByPrice(prix1, prix2, productService.GetAllCartByUserId(1)));
			return listAllCartByUserId;
		}
	}

	public List<Cart> getSearch() throws ParseException {
		return listAllCartByUserId;
	}

	public String DisplayMyCartHistory() throws ParseException {
		Search();
		String navigateTo = "/Consomi-web/template/ProductCart/HistoryPurchase.jsf";
		return navigateTo;
	}

	public boolean TestImage(Product p) {
		if (p.imageString.length() > 20)
			return true;
		return false;
	}
	public boolean TestImage1(String image) {
		if (image.length() > 20)
			return true;
		return false;
	}

	

	private Part logo;

	public Part getLogo() {
		return logo;
	}

	public void setLogo(Part logo) {
		this.logo = logo;
	}

	public void doUpload() {

		try {
			InputStream in = logo.getInputStream();
			img1 = logo.getSubmittedFileName();
			File f = new File(
					"C:\\Users\\Hsine\\Desktop\\souhaib\\PiDevJee-master\\POC_PI_AWS-web\\src\\main\\webapp\\Ressources\\Uploads\\"
							+ logo.getSubmittedFileName());
			System.out.println("hsiiiiiine" + f.getAbsolutePath());
			f.createNewFile();
			FileOutputStream out = new FileOutputStream(f);

			byte[] buffer = new byte[1024];
			int length;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			out.close();
			in.close();

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

	}

	public String addProduct() throws ParseException {
		System.out.println("hhhhhhh" + imageString);
		productService.Create(new Product(nom, prix, img1, category, quantite, description));

		this.setNom(null);
		this.setPrix(0);
		this.setCategory(null);
		this.setQuantite(0);
		this.setDescription(null);
		this.getSearch();

		return "/ProductCart/Index?faces-redirect=true";

	}

	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String Details(Product p)  {
		System.out.println("hhhhhhh11" + p.imageString);
		this.setId(p.getId());
		this.setNom(p.getNom());
		this.setPrix(p.getPrix());
		this.setCategory(p.getCategory());
		this.setQuantite(p.getQuantite());
		this.setDescription(p.getDescription());
		this.setImageString( p.imageString);

		return "/ProductCart/DisplaySelectedProduct?faces-redirect=true";

	}

	public String AddToCart() {
productService.AddProductToMyCart(id, new Product(id,quantite));
		return "/ProductCart/Index?faces-redirect=true";
	}
	private List<CartLine> Cartlines= new ArrayList<CartLine>();
	public List<CartLine> getCartlines() {
		Cartlines=productService.MyCart();
		
		return Cartlines;
	}
	public String Confirm() {
		System.out.println("hhhhhh");
		productService.ConfirmPurchase();
		return "/ProductCart/HistoryPurchase?faces-redirect=true";

	}
	public String Remove(CartLine c) {
		productService.DeleteCart(c.cartId);
		return "/ProductCart/MyCart?faces-redirect=true";
	}
	public String UpdateQuan(CartLine c) {
		this.setId(c.getId());
		this.setNom(c.getNomProd());
		this.setPrix(c.getPrixunitaire());
		this.setCategory(c.getMyProduct().getCategory());
		this.setQuantite(c.getQuantiteChoisie());
		this.setDescription(c.getMyProduct().getDescription());
		this.imageString = c.getMyProduct().getImageString();
		return "/ProductCart/EditCartLine?faces-redirect=true";
	}
	public String PutCartLine() throws InterruptedException {

		productService.Update(getId(),
				new CartLine(getId(),getQuantite()));
		this.setId(0);
		this.setQuantite(0);
		

		return "/ProductCart/MyCart?faces-redirect=true";
	}

}
