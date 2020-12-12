package by.mvashkewi4.wt.dao.impl;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.dao.connection.ConnectionProvider;
import by.mvashkewi4.wt.dao.connection.ConnectionProviderException;
import by.mvashkewi4.wt.dao.interfaces.PassportDataDAO;
import by.mvashkewi4.wt.entity.PassportData;

import java.sql.*;

public class SQLPassportDataDAO implements PassportDataDAO {
    private static final String ADD_PASSPORT_DATA_QUERY = "INSERT INTO `passport_data` (`series`, `number`, `personal_number`, `issue_date`, `validity_date`, `birth_date`, `sex`, `authority`, `name`, `surname`, `middlename`, `name_eng`, `surname_eng`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final ConnectionProvider connectionProvider;

    public SQLPassportDataDAO(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    static int add(Connection connection, PassportData passportData) throws DAOException {
        try {
            PreparedStatement addPassportDataStatement = connection.prepareStatement(ADD_PASSPORT_DATA_QUERY, Statement.RETURN_GENERATED_KEYS);

            addPassportDataStatement.setString(1, passportData.getSeries());
            addPassportDataStatement.setInt(2, passportData.getNumber());
            addPassportDataStatement.setString(3, passportData.getPersonalNumber());
            addPassportDataStatement.setDate(4, passportData.getIssueDate());
            addPassportDataStatement.setDate(5, passportData.getValidityDate());
            addPassportDataStatement.setDate(6, passportData.getBirthDate());
            addPassportDataStatement.setInt(7, passportData.getSex().getValue());
            addPassportDataStatement.setString(8, passportData.getAuthority());
            addPassportDataStatement.setString(9, passportData.getName());
            addPassportDataStatement.setString(10, passportData.getSurname());
            addPassportDataStatement.setString(11, passportData.getMiddlename());
            addPassportDataStatement.setString(12, passportData.getNameEng());
            addPassportDataStatement.setString(13, passportData.getSurnameEng());
            int rowsAffected = addPassportDataStatement.executeUpdate();

            if (rowsAffected != 0) {
                try (ResultSet generatedKeys = addPassportDataStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            addPassportDataStatement.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        throw new DAOException("Creating passportData failed, no ID obtained.");
    }

    @Override
    public int add(PassportData passportData) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            return add(connection, passportData);

        } catch (ConnectionProviderException | SQLException e) {
            throw new DAOException(e);
        }
    }
}
