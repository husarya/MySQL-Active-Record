package org.majesty.mar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.majesty.mar.DbClient;
import org.majesty.mar.User;

public class UserGod {


	private static final String createUserQuery = "INSERT INTO Users(Username,Email,Password) VALUES (?,?,?)";
	private static final String deleteUserQuery = "DELETE FROM Users where id = ?";
	private static final String findUsersQuery = "SELECT * FROM Users";
	private static final String findUserByIdQuery = "Select * from Users where id = ?";
	private static final String editUserQuery = "UPDATE	Users SET Username = ? , Email = ?, Password = ? WHERE	id = ?";


	public User read(Integer UserId) {
		User User = new User();
		try (Connection connection = DbClient.getConnection();
				PreparedStatement statement = connection.prepareStatement(findUserByIdQuery);) {
			statement.setInt(1, UserId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					User.setId(resultSet.getInt("id"));
					User.setUsername(resultSet.getString("Username"));
					User.setEmail(resultSet.getString("Email"));
					User.setPassword(resultSet.getString("Password"));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Nope  :|");
		}
		return User;

	}

	public void findAll() {
		List<User> usersList = new ArrayList<>();
		try (Connection connection = DbClient.getConnection();
				PreparedStatement statement = connection.prepareStatement(findUsersQuery);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				User addUserToList = new User();
				addUserToList.setId(resultSet.getInt("id"));
				addUserToList.setUsername(resultSet.getString("Username"));
				addUserToList.setEmail(resultSet.getString("Email"));
				addUserToList.setPassword(resultSet.getString("Password"));
				usersList.add(addUserToList);

			}
			for (User list : usersList) {
				System.out.println(list);
			}

		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Nope  :|");
		}

	}

	public User create(User User) {
		try (Connection connection = DbClient.getConnection();
				PreparedStatement insertStm = connection.prepareStatement(createUserQuery,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			insertStm.setString(1, User.getUsername());
			insertStm.setString(2, User.getEmail());
			insertStm.setString(3, User.getPassword());
			int result = insertStm.executeUpdate();

			if (result != 1) {
				throw new RuntimeException("Execute update returned " + result);
			}

			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					User.setId(generatedKeys.getInt(1));
					return User;

				}else {
					throw new RuntimeException("Generated key was not found");
				}

			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Nope  :|");
		}
		return null;
	}

	public void delete(Integer UserId) {
		try (Connection connection = DbClient.getConnection();
				PreparedStatement statement = connection.prepareStatement(deleteUserQuery);) {
			statement.setInt(1, UserId);
			statement.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Nope  :|");
		}
	}

	public void update(User User) {
		try (Connection connection = DbClient.getConnection();
				PreparedStatement statement = connection.prepareStatement(editUserQuery);) {
			statement.setInt(4, User.getId());
			statement.setString(1, User.getUsername());
			statement.setString(2, User.getEmail());
			statement.setString(3, User.getPassword());

			statement.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Nope  :|");
		}

	}

}



