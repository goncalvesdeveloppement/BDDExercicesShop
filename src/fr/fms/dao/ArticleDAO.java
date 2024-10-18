package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class ArticleDAO implements DAO<Article> {
	public void create(Article obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "INSERT INTO T_Articles(Description, Brand, UnitaryPrice) VALUES(?, ?, ?);";

			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setString(1, obj.getDescription());
				statement.setString(2, obj.getBrand());
				statement.setDouble(3, obj.getUnitaryPrice());

				if (statement.executeUpdate() == 1)
					System.out.println("Insertion OK.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Article read(int id) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "SELECT * FROM T_Articles WHERE IdArticle = " + id + ";";

			try (java.sql.Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
					while (resultSet.next()) {
						int idArticle = resultSet.getInt(1);
						String description = resultSet.getString(2);
						String brand = resultSet.getString(3);
						double unitaryPrice = resultSet.getDouble(4);

						return new Article(idArticle, description, brand, unitaryPrice);
					}

					return null; // Replace further by a custom exception.
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean update(Article obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "UPDATE T_Articles SET Description = ?, Brand = ?, UniTaryPrice = ? WHERE IdArticle = "
					+ obj.getIdArticle() + ";";

			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setString(1, obj.getDescription());
				statement.setString(2, obj.getBrand());
				statement.setDouble(3, obj.getUnitaryPrice());

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

	public boolean delete(Article obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "DELETE FROM T_Articles WHERE IdArticle = "
					+ obj.getIdArticle() + ";";

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

	public ArrayList<Article> readAll() {
		DBAccess.getInstance().initCredentials();
		ArrayList<Article> articles = new ArrayList<Article>();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "SELECT * FROM T_Articles;";

			try (java.sql.Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
					while (resultSet.next()) {
						int idArticle = resultSet.getInt(1);
						String description = resultSet.getString(2);
						String brand = resultSet.getString(3);
						double unitaryPrice = resultSet.getDouble(4);

						articles.add(new Article(idArticle, description, brand, unitaryPrice));
					}

					return articles;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
