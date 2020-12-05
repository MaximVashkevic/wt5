package by.mvashkewi4.wt.service;

import by.mvashkewi4.wt.entity.Address;
import by.mvashkewi4.wt.entity.ClientData;
import by.mvashkewi4.wt.entity.PassportData;
import by.mvashkewi4.wt.entity.User;

public interface ClientService {
    User logIn(String login, String password) throws ServiceException;

    boolean signUp(User user, ClientData clientData, PassportData passportData, Address address) throws ServiceException;
}