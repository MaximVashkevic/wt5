package by.mvashkewi4.wt.dao;

import by.mvashkewi4.wt.dao.connection.ConnectionProvider;
import by.mvashkewi4.wt.dao.impl.*;
import by.mvashkewi4.wt.dao.interfaces.*;

public class DAOProvider {
    private static DAOProvider instance;
    private static ConnectionProvider connectionProvider;
    private final UserDAO userDAO;
    private final PassportDataDAO passportDataDAO;
    private final AddressDAO addressDAO;
    private final ClientDataDAO clientDataDAO;
    private final AccountDAO accountDAO;
    private final CardDAO cardDAO;
    private final TransactionDAO transactionDAO;

    private DAOProvider() {
        userDAO = new SQLUserDAO(connectionProvider);
        passportDataDAO = new SQLPassportDataDAO(connectionProvider);
        addressDAO = new SQLAddressDAO(connectionProvider);
        clientDataDAO = new SQLClientDataDAO(connectionProvider);
        accountDAO = new SQLAccountDAO(connectionProvider);
        cardDAO = new SQLCardDAO(connectionProvider);
        transactionDAO = new SQLTransactionDAO(connectionProvider);
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

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public CardDAO getCardDAO() {
        return cardDAO;
    }

    public TransactionDAO getTransactionDAO() {
        return transactionDAO;
    }
}
