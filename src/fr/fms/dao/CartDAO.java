package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import fr.fms.entities.Article;
import fr.fms.entities.Cart;
import fr.fms.entities.User;

public class CartDAO implements DAO<Cart> {
	public void create(Cart obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "INSERT INTO T_Carts(IdUser) VALUES(?);";

			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setInt(1, obj.getOwner().getIdUser());
				if (statement.executeUpdate() == 1)
					System.out.println("Insertion OK.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Cart read(int id) {
		DBAccess.getInstance().initCredentials();
		UserDAO udao = new UserDAO();
		ArticleDAO adao = new ArticleDAO();
		Cart cart = null;

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "SELECT * FROM T_Carts WHERE IdCart = " + id + ";";

			try (java.sql.Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
					while (resultSet.next()) {
						int idCart = resultSet.getInt(1);
						User user = udao.read(resultSet.getInt(2));

						cart = new Cart(idCart, user);

						sqlQuery = "SELECT * FROM TR_ArticleCarts WHERE IdCart = " + id + ";";

						try (java.sql.Statement statement2 = connection.createStatement()) {
							try (ResultSet resultSet2 = statement2.executeQuery(sqlQuery)) {
								while (resultSet2.next()) {
									Article article = adao.read(resultSet2.getInt(3));
									int quantity = resultSet2.getInt(4);

									cart.add(article, quantity);
								}

								return cart;
							}
						}
					}

					return null; // Replace further by a custom exception.
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean update(Cart obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "UPDATE T_Carts SET IdUser = ? WHERE IdCart = " + obj.getIdCart()
					+ ";";

			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setInt(1, obj.getOwner().getIdUser());

				if (statement.executeUpdate() == 1) {
					sqlQuery = "DELETE FROM TR_ArticleCarts WHERE IdCart = " + obj.getIdCart() + ";";
					
					try (PreparedStatement statement2 = connection.prepareStatement(sqlQuery)) {
						if (statement2.executeUpdate() == 1) {
							for (Map.Entry<Article, Integer> cartLine : obj.getCartContent().entrySet()) {
								sqlQuery = "INSERT INTO TR_ArticleCarts (IdCart, IdArticle, Quantity) VALUES(?, ?, ?);";

								try (PreparedStatement statement3 = connection.prepareStatement(sqlQuery)) {
									statement3.setInt(1, obj.getIdCart());
									statement3.setInt(1, cartLine.getKey().getIdArticle());
									statement3.setInt(1, cartLine.getValue());

									if (statement3.executeUpdate() == 1) {
										System.out.println("Article mis à jour dans le panier.");
									}
									
									return true;
								}
							}
						}
						
						return true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean delete(Cart obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "DELETE FROM T_Carts WHERE IdCart = " + obj.getIdCart() + ";";

			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				if (statement.executeUpdate() == 1)
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<Cart> readAll() {
		DBAccess.getInstance().initCredentials();
		ArrayList<Cart> carts = new ArrayList<Cart>();
		Cart cart = null;
		UserDAO udao = new UserDAO();
		ArticleDAO adao = new ArticleDAO();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "SELECT * FROM T_Carts;";

			try (java.sql.Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
					while (resultSet.next()) {
						int idCart = resultSet.getInt(1);
						User user = udao.read(resultSet.getInt(2));

						cart = new Cart(idCart, user);

						sqlQuery = "SELECT * FROM TR_ArticleCarts;";

						try (java.sql.Statement statement2 = connection.createStatement()) {
							try (ResultSet resultSet2 = statement2.executeQuery(sqlQuery)) {
								while (resultSet2.next()) {
									Article article = adao.read(resultSet2.getInt(3));
									int quantity = resultSet2.getInt(4);

									cart.add(article, quantity);
								}

								carts.add(cart);
							}
						}
					}

					return null; // Replace further by a custom exception.
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
