package by.mvashkewi4.wt.service.impl;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.dao.DAOProvider;
import by.mvashkewi4.wt.dao.UserDAO;
import by.mvashkewi4.wt.entity.Address;
import by.mvashkewi4.wt.entity.ClientData;
import by.mvashkewi4.wt.entity.PassportData;
import by.mvashkewi4.wt.entity.User;
import by.mvashkewi4.wt.service.ClientService;
import by.mvashkewi4.wt.service.ServiceException;
import by.mvashkewi4.wt.service.validation.CredentialsValidator;

public class ClientServiceImpl implements ClientService {
    @Override
    public User logIn(String login, String password) throws ServiceException {
        if (!CredentialsValidator.isCorrect(login, password)) {
            throw new ServiceException("message");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        User user;

        try {
            user = userDAO.logIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public boolean register(User user, ClientData clientData, PassportData passportData, Address address) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        try {
            return userDAO.register(user, clientData, passportData, address);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
