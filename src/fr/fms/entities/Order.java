package fr.fms.entities;

public class Order {
	/* ------------ ATTRIBUTES ------------ */
	
	private int idOrder;
	private Cart cart;
	private int status; // 0 = created, 1+ = add some shipment status...
	
	/* ------------ CONSTRUCTORS ------------ */
	
	public Order(int idOrder, Cart cart, int status) {
		this.setIdOrder(idOrder);
		this.setCart(cart);
		this.setStatus(status);
	}
	
	public Order(Cart cart, int status) {
		this.setIdOrder(0);
		this.setCart(cart);
		this.setStatus(status);
	}

	/* ------------ METHODS ------------ */
	
	@Override
	public String toString() {
		return "Order ["
				+ "idOrder = " + this.getIdOrder() + ", "
				+ "cart = " + this.getCart() + ", "
				+ "status = " + this.getStatus() + "]\n";
	}


	/* ------------ ACCESSORS ------------ */
	
	public int getIdOrder() {
		return idOrder;
	}
	
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
