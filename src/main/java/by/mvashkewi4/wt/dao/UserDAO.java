package by.mvashkewi4.wt.dao;

import by.mvashkewi4.wt.entity.Address;
import by.mvashkewi4.wt.entity.ClientData;
import by.mvashkewi4.wt.entity.PassportData;
import by.mvashkewi4.wt.entity.User;

public interface UserDAO {
    User logIn(String login, String password) throws DAOException;

    boolean signUp(User user, ClientData clientData, PassportData passportData, Address address) throws DAOException;
}
