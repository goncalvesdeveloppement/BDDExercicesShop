package fr.fms.entities;

import java.util.HashMap;

public class Cart {
	/* ------------ ATTRIBUTES ------------ */
	
	private int idCart;
	private User owner;
	private HashMap<Article, Integer> cartContent = new HashMap<Article, Integer>();
	
	/* ------------ CONSTRUCTORS ------------ */
	
	public Cart(int idCart, User owner) {
		this.setIdCart(idCart);
		this.setOwner(owner);
	}
	
	public Cart(User owner) {
		this.setIdCart(0);
		this.setOwner(owner);
	}
	
	/* ------------ METHODS ------------ */
	
	public void add(Article article, int quantity) {
		this.getCartContent().put(article, quantity);
	}
	
	public void remove(Article article) {
		this.getCartContent().remove(article);
	}
	
	@Override
	public String toString() {
		return "Cart ["
				+ "idCart = " + this.getIdCart() + ", "
				+ "owner = " + this.getOwner() + ", "
				+ "cartContent = " + this.getCartContent()
				+ "]\n";
	}
	
	/* ------------ ACCESSORS ------------ */

	public int getIdCart() {
		return idCart;
	}

	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public HashMap<Article, Integer> getCartContent() {
		return cartContent;
	}

	public void setCartContent(HashMap<Article, Integer> cartContent) {
		this.cartContent = cartContent;
	}
}
