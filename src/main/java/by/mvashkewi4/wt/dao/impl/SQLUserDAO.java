package by.mvashkewi4.wt.dao.impl;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.dao.UserDAO;
import by.mvashkewi4.wt.entity.Address;
import by.mvashkewi4.wt.entity.ClientData;
import by.mvashkewi4.wt.entity.PassportData;
import by.mvashkewi4.wt.entity.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class SQLUserDAO implements UserDAO {
    private static final String connectionString = "jdbc:mysql://localhost:3306/paymentsdb?useUnicode=true&serverTimezone=UTC";
    private static final String loginQuery = "SELECT * FROM users where login = ?;";
    private static final String addAddressQuery =
            "INSERT INTO `addresses` (`country`, `region`, `district`, `settlement_type`, `settlement_name`, `street_type`, `street_name`, `house`, `building`, `flat`, `zipcode`, `phone_number`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String addPassportDataQuery = "INSERT INTO `passport_data` (`series`, `number`, `personal_number`, `issue_date`, `validity_date`, `birth_date`, `sex`, `authority`, `name`, `surname`, `middlename`, `name_eng`, `surname_eng`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String addUserQuery = "INSERT INTO `users` (`login`, `password`, `role_id`) VALUES (?, ?, ?);";
    private static final String addClientDataQuery = "INSERT INTO `client_data` (`client_id`, `passport_id`, `address_id`, `codeword`) VALUES (?, ?, ?, ?);";
    private static Connection connection = null;
    private static PreparedStatement loginStatement = null;
    private static PreparedStatement addAddressStatement = null;
    private static PreparedStatement addPassportDataStatement = null;
    private static PreparedStatement addUserStatement = null;
    private static PreparedStatement addClientDataStatement = null;

    static {
        final String userName = "user";
        final String password = "password";
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(connectionString, userName, password);
            loginStatement = connection.prepareStatement(loginQuery);
            addAddressStatement = connection.prepareStatement(addAddressQuery, Statement.RETURN_GENERATED_KEYS);
            addPassportDataStatement = connection.prepareStatement(addPassportDataQuery, Statement.RETURN_GENERATED_KEYS);
            addUserStatement = connection.prepareStatement(addUserQuery, Statement.RETURN_GENERATED_KEYS);
            addClientDataStatement = connection.prepareStatement(addClientDataQuery, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static int addAddress(Address address) throws DAOException {
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new DAOException("Creating address failed, no ID obtained.");
    }

    private static int addPassportData(PassportData passportData) throws DAOException {
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new DAOException("Creating passportData failed, no ID obtained.");
    }

    private static int addClientData(ClientData clientData) throws DAOException {
        try {
            addClientDataStatement.setInt(1, clientData.getClientId());
            addClientDataStatement.setInt(2, clientData.getPassportId());
            addClientDataStatement.setInt(3, clientData.getAddressId());
            addClientDataStatement.setString(4, clientData.getCodeWord());
            int rowsAffected = addClientDataStatement.executeUpdate();

            if (rowsAffected != 0) {
                try (ResultSet generatedKeys = addUserStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new DAOException("Creating clientData failed, no ID obtained.");
    }

    private static int addUser(User user) throws DAOException {
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new DAOException("Creating user failed, no ID obtained.");
    }

    @Override
    public User logIn(String login, String password) throws DAOException {
        try {
            loginStatement.setString(1, login);
            ResultSet result = loginStatement.executeQuery();
            if (result.next() && result.getString("password").equals(password)) {
                return new User(
                        result.getInt("id"),
                        result.getString("login"),
                        "",
                        result.getInt("role_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException(throwables);
        }
        return null;
    }

    @Override
    public boolean signUp(User user, ClientData clientData, PassportData passportData, Address address) throws DAOException {
        try {
            connection.setAutoCommit(false);

            int addressId = addAddress(address);
            int passportDataId = addPassportData(passportData);
            int userId = addUser(user);

            clientData.setAddressId(addressId);
            clientData.setPassportId(passportDataId);
            clientData.setClientId(userId);
            int clientDataId = addClientData(clientData);
            connection.commit();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (DAOException throwables) {
            throwables.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throw throwables;
        }
        return false;
    }
}
