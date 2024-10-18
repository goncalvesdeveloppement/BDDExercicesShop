package fr.fms;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.mariadb.jdbc.Statement;

import fr.fms.dao.ArticleDAO;
import fr.fms.dao.CartDAO;
import fr.fms.dao.OrderDAO;
import fr.fms.dao.UserDAO;
import fr.fms.entities.Article;
import fr.fms.entities.Cart;
import fr.fms.entities.User;

public class EcomApp {
	private static boolean isLogged = false;
	private static Scanner scan = new Scanner(System.in);
	private static ArticleDAO adao = new ArticleDAO();
	private static CartDAO cdao = new CartDAO();
	private static OrderDAO odao = new OrderDAO();
	private static UserDAO udao = new UserDAO();
	private static User user;

	public static void main(String[] args) {
		do {
			user = userLogin();
		} while (user == null);

		if (user.getIdUser() == -1) {
			System.out.println("\n👋🏻 Bye bye.");
		}
		else {
			System.out.print("\b \r");
			System.out.println("Bienvenue, " + user.getLogin() + ".\n\nVoici les articles en stock :\n" + adao.readAll());

			// menu etc
		}
	}
	
	public static User userLogin() {
		while (!isLogged) {
			String username, password;
			
			System.out.print("Bienvenue sur MonShop.\nEntrez votre nom d'utilisateur : \n(ou tapez 'quitter' pour quitter)\n\n-> ");
			username = scan.next();
			
			if (username.equals("quitter"))
				return new User(-1, "", "");
			
			System.out.print("\nEntrez votre mot de passe : \n\n-> ");
			password = scan.next();

			for (User tempUser : udao.readAll()) {
				if (tempUser.getLogin().equals(username) && tempUser.getPassword().equals(password)) {
					isLogged = true;
					return tempUser;
				}
			}

			if (!isLogged) {
				System.out.println("\n❌ Nom d'utilisateur ou mot de passe incorrect...\n");
				return null;
			}
		}
		
		return null;
	}
}