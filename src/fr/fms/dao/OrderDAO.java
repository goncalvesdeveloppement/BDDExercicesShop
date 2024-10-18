package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.Cart;
import fr.fms.entities.Order;

public class OrderDAO implements DAO<Order> {
	public void create(Order obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "INSERT INTO T_Orders(IdCart, Status) VALUES(?, ?);";

			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setInt(1, obj.getCart().getIdCart());
				statement.setInt(2, obj.getStatus());

				if (statement.executeUpdate() == 1)
					System.out.println("Insertion OK.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Order read(int id) {
		DBAccess.getInstance().initCredentials();
		CartDAO cdao = new CartDAO(); 

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "SELECT * FROM T_Orders WHERE IdOrder = " + id + ";";

			try (java.sql.Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
					while (resultSet.next()) {
						int idOrder = resultSet.getInt(1);
						Cart cart = cdao.read(resultSet.getInt(2));
						int status = resultSet.getInt(3);

						return new Order(idOrder, cart, status);
					}

					return null; // Replace further by a custom exception.
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean update(Order obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "UPDATE T_Orders SET IdCart = ?, Status = ? WHERE IdOrder = "
					+ obj.getIdOrder() + ";";

			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setInt(1, obj.getCart().getIdCart());
				statement.setInt(2, obj.getStatus());

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

	public boolean delete(Order obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "DELETE FROM T_Orders WHERE IdOrder = "
					+ obj.getIdOrder() + ";";

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

	public ArrayList<Order> readAll() {
		DBAccess.getInstance().initCredentials();
		ArrayList<Order> orders = new ArrayList<Order>();
		CartDAO cdao = new CartDAO();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "SELECT * FROM T_Orders;";

			try (java.sql.Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
					while (resultSet.next()) {
						int idOrder = resultSet.getInt(1);
						Cart cart = cdao.read(resultSet.getInt(2));
						int status = resultSet.getInt(3);

						orders.add(new Order(idOrder, cart, status));
					}

					return orders;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
