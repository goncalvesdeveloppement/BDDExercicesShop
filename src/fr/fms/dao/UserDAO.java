package fr.fms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.User;

public class UserDAO implements DAO<User> {
	public void create(User obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "INSERT INTO T_Users(Login, Password) VALUES(?, ?);";

			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setString(1, obj.getLogin());
				statement.setString(2, obj.getPassword());

				if (statement.executeUpdate() == 1)
					System.out.println("Insertion OK.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User read(int id) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "SELECT * FROM T_Users WHERE IdUser = " + id + ";";

			try (java.sql.Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
					while (resultSet.next()) {
						int idUser = resultSet.getInt(1);
						String login = resultSet.getString(2);
						String password = resultSet.getString(3);

						return new User(idUser, login, password);
					}

					return null; // Replace further by a custom exception.
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean update(User obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "UPDATE T_Users SET Login = ?, Password = ? WHERE IdUser = "
					+ obj.getIdUser() + ";";

			try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
				statement.setString(1, obj.getLogin());
				statement.setString(2, obj.getPassword());

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

	public boolean delete(User obj) {
		DBAccess.getInstance().initCredentials();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "DELETE FROM T_Users WHERE IdUser = "
					+ obj.getIdUser() + ";";

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

	public ArrayList<User> readAll() {
		DBAccess.getInstance().initCredentials();
		ArrayList<User> users = new ArrayList<User>();

		try (Connection connection = DBAccess.getInstance().getConnection()) {
			String sqlQuery = "SELECT * FROM T_Users;";

			try (java.sql.Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
					while (resultSet.next()) {
						int idUser = resultSet.getInt(1);
						String login = resultSet.getString(2);
						String password = resultSet.getString(3);

						users.add(new User(idUser, login, password));
					}

					return users;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
