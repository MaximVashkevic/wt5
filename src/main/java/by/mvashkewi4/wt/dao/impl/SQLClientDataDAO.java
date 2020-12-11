package by.mvashkewi4.wt.dao.impl;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.dao.connection.ConnectionProvider;
import by.mvashkewi4.wt.dao.connection.ConnectionProviderException;
import by.mvashkewi4.wt.dao.interfaces.ClientDataDAO;
import by.mvashkewi4.wt.entity.ClientData;

import java.sql.*;

public class SQLClientDataDAO implements ClientDataDAO {
    private static final String ADD_CLIENT_DATA_QUERY = "INSERT INTO `client_data` (`client_id`, `passport_id`, `address_id`, `codeword`) VALUES (?, ?, ?, ?);";
    private final ConnectionProvider connectionProvider;

    public SQLClientDataDAO(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public int add(ClientData clientData) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement addClientDataStatement = connection.prepareStatement(ADD_CLIENT_DATA_QUERY, Statement.RETURN_GENERATED_KEYS);

            addClientDataStatement.setInt(1, clientData.getClientId());
            addClientDataStatement.setInt(2, clientData.getPassportId());
            addClientDataStatement.setInt(3, clientData.getAddressId());
            addClientDataStatement.setString(4, clientData.getCodeWord());
            int rowsAffected = addClientDataStatement.executeUpdate();

            if (rowsAffected != 0) {
                try (ResultSet generatedKeys = addClientDataStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
        throw new DAOException("Creating clientData failed, no ID obtained.");
    }
}

