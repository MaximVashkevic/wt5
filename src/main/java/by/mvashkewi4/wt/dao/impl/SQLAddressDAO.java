package by.mvashkewi4.wt.dao.impl;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.dao.connection.ConnectionProvider;
import by.mvashkewi4.wt.dao.connection.ConnectionProviderException;
import by.mvashkewi4.wt.dao.interfaces.AddressDAO;
import by.mvashkewi4.wt.entity.Address;

import java.sql.*;

public class SQLAddressDAO implements AddressDAO {
    private static final String ADD_ADDRESS_QUERY =
            "INSERT INTO `addresses` (`country`, `region`, `district`, `settlement_type`, `settlement_name`, `street_type`, `street_name`, `house`, `building`, `flat`, `zipcode`, `phone_number`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private final ConnectionProvider connectionProvider;

    public SQLAddressDAO(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public int add(Address address) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement addAddressStatement = connection.prepareStatement(ADD_ADDRESS_QUERY, Statement.RETURN_GENERATED_KEYS);
            addAddressStatement.setString(1, address.getCountry());
            addAddressStatement.setString(2, address.getRegion());
            addAddressStatement.setString(3, address.getDistrict());
            addAddressStatement.setString(4, address.getSettlementType());
            addAddressStatement.setString(5, address.getSettlementName());
            addAddressStatement.setString(6, address.getStreetType());
            addAddressStatement.setString(7, address.getStreetName());
            addAddressStatement.setShort(8, address.getHouse());
            addAddressStatement.setByte(9, address.getBuilding());
            addAddressStatement.setInt(10, address.getFlat());
            addAddressStatement.setString(11, address.getZipcode());
            addAddressStatement.setString(12, address.getPhoneNumber());
            int rowsAffected = addAddressStatement.executeUpdate();

            if (rowsAffected != 0) {
                try (ResultSet generatedKeys = addAddressStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
        throw new DAOException("Creating address failed, no ID obtained.");
    }
}
