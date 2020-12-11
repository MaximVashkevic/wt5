package by.mvashkewi4.wt.dao;

import by.mvashkewi4.wt.dao.connection.ConnectionProvider;
import by.mvashkewi4.wt.dao.impl.SQLAddressDAO;
import by.mvashkewi4.wt.dao.impl.SQLClientDataDAO;
import by.mvashkewi4.wt.dao.impl.SQLPassportDataDAO;
import by.mvashkewi4.wt.dao.impl.SQLUserDAO;
import by.mvashkewi4.wt.dao.interfaces.AddressDAO;
import by.mvashkewi4.wt.dao.interfaces.ClientDataDAO;
import by.mvashkewi4.wt.dao.interfaces.PassportDataDAO;
import by.mvashkewi4.wt.dao.interfaces.UserDAO;

public class DAOProvider {
    private static DAOProvider instance;
    private static ConnectionProvider connectionProvider;
    private final UserDAO userDAO;
    private final PassportDataDAO passportDataDAO;
    private final AddressDAO addressDAO;
    private final ClientDataDAO clientDataDAO;

    private DAOProvider() {
        userDAO = new SQLUserDAO(connectionProvider);
        passportDataDAO = new SQLPassportDataDAO(connectionProvider);
        addressDAO = new SQLAddressDAO(connectionProvider);
        clientDataDAO = new SQLClientDataDAO(connectionProvider);
    }

    public static void init(ConnectionProvider connectionProvider) {
        DAOProvider.connectionProvider = connectionProvider;
        instance = new DAOProvider();
    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public PassportDataDAO getPassportDataDAO() {
        return passportDataDAO;
    }

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public ClientDataDAO getClientDataDAO() {
        return clientDataDAO;
    }
}
