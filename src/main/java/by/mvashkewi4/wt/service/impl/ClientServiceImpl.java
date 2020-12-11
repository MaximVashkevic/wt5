package by.mvashkewi4.wt.service.impl;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.dao.DAOProvider;
import by.mvashkewi4.wt.dao.interfaces.AddressDAO;
import by.mvashkewi4.wt.dao.interfaces.ClientDataDAO;
import by.mvashkewi4.wt.dao.interfaces.PassportDataDAO;
import by.mvashkewi4.wt.dao.interfaces.UserDAO;
import by.mvashkewi4.wt.entity.Address;
import by.mvashkewi4.wt.entity.ClientData;
import by.mvashkewi4.wt.entity.PassportData;
import by.mvashkewi4.wt.entity.User;
import by.mvashkewi4.wt.service.ClientService;
import by.mvashkewi4.wt.service.ServiceException;
import by.mvashkewi4.wt.service.validation.CredentialsValidator;

public class ClientServiceImpl implements ClientService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ClientServiceImpl.class);
    @Override
    public User logIn(String login, String password) throws ServiceException {
        if (!CredentialsValidator.isCorrect(login, password)) {
            throw new ServiceException("login or password doesn't match criteria");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserDAO();

        User user;

        try {
            user = userDAO.logIn(login, password);
        } catch (DAOException e) {
            log.error("DAO exception", e);
            throw new ServiceException(e);
        }

        return user;
    }

    @Override
    public boolean signUp(User user, ClientData clientData, PassportData passportData, Address address) throws ServiceException {
        if (!CredentialsValidator.isCorrect(user.getLogin(), user.getPassword())) {
            throw new ServiceException("login or password doesn't match criteria");
        }

        DAOProvider daoProvider = DAOProvider.getInstance();
        PassportDataDAO passportDataDAO = daoProvider.getPassportDataDAO();
        ClientDataDAO clientDataDAO = daoProvider.getClientDataDAO();
        AddressDAO addressDAO = daoProvider.getAddressDAO();
        UserDAO userDAO = daoProvider.getUserDAO();

        try {
            int passportDataId = passportDataDAO.add(passportData);
            int addressId = addressDAO.add(address);
            int userId = userDAO.add(user);

            clientData.setAddressId(addressId);
            clientData.setPassportId(passportDataId);
            clientData.setClientId(userId);

            clientDataDAO.add(clientData);
            return true;
        } catch (DAOException e) {
            log.error("DAO exception", e);
            throw new ServiceException(e);
        }
    }
}
