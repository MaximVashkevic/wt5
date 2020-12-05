package by.mvashkewi4.wt.dao;

import by.mvashkewi4.wt.dao.impl.SQLUserDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();
    private final UserDAO userDAO = new SQLUserDAO();

    private DAOProvider() {
    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
