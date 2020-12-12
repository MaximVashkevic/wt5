package by.mvashkewi4.wt.dao.impl;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.dao.connection.ConnectionProvider;
import by.mvashkewi4.wt.dao.connection.ConnectionProviderException;
import by.mvashkewi4.wt.dao.interfaces.UserDAO;
import by.mvashkewi4.wt.entity.User;

import java.sql.*;

public class SQLUserDAO implements UserDAO {
    private static final String LOGIN_QUERY = "SELECT * FROM users where login = ?;";
    private static final String ADD_USER_QUERY = "INSERT INTO `users` (`login`, `password`, `role_id`) VALUES (?, ?, ?);";

    private final ConnectionProvider connectionProvider;

    public SQLUserDAO(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public User logIn(String login, String password) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement loginStatement = connection.prepareStatement(LOGIN_QUERY, Statement.RETURN_GENERATED_KEYS);
            loginStatement.setString(1, login);
            ResultSet result = loginStatement.executeQuery();
            if (result.next() && result.getString("password").equals(password)) {
                return new User(
                        result.getInt("id"),
                        result.getString("login"),
                        "",
                        result.getInt("role_id"));
            }
            loginStatement.close();
        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
        return null;
    }

    @Override
    public int add(User user) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement addUserStatement = connection.prepareStatement(ADD_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            addUserStatement.setString(1, user.getLogin());
            addUserStatement.setString(2, user.getPassword());
            addUserStatement.setInt(3, user.getRoleId());
            int rowsAffected = addUserStatement.executeUpdate();

            if (rowsAffected != 0) {
                try (ResultSet generatedKeys = addUserStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            addUserStatement.close();
        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
        throw new DAOException("Creating user failed, no ID obtained.");
    }
}
