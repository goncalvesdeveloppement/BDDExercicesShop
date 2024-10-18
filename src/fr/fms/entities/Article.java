package fr.fms.entities;

public class Article {
	/* ------------ ATTRIBUTES ------------ */
	
	private int idArticle;
	private String description;
	private String brand;
	private double unitaryPrice;
	
	/* ------------ CONSTRUCTORS ------------ */
	
	public Article(int idArticle, String description, String brand, double unitaryPrice) {
		this.setIdArticle(idArticle);
		this.setDescription(description);
		this.setBrand(brand);
		this.setUnitaryPrice(unitaryPrice);
	}
	
	public Article(String description, String brand, double unitaryPrice) {
		this.setIdArticle(0);
		this.setDescription(description);
		this.setBrand(brand);
		this.setUnitaryPrice(unitaryPrice);
	}
	
	/* ------------ METHODS ------------ */
	
	@Override
	public String toString() {
		return "Article ["
				+ "idArticle = " + this.getIdArticle() + ", "
				+ "description = \"" + this.getDescription() + "\", "
				+ "brand = \"" + this.getBrand() + "\", "
				+ "unitaryPrice = " + this.getUnitaryPrice()
				+ "]\n";
	}
	
	/* ------------ ACCESSORS ------------ */
	
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getUnitaryPrice() {
		return unitaryPrice;
	}
	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}
}
