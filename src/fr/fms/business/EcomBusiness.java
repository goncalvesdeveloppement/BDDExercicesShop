package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Article;
import fr.fms.entities.User;

public interface EcomBusiness {
	public void addToCart(User user, Article article, int quantity);
	public void removeFromCart(User user, Article article);
	public ArrayList<Article> getCart(User user);
	public void Order(User user);
}
