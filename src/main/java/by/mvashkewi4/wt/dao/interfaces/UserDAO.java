package by.mvashkewi4.wt.dao.interfaces;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.entity.User;

public interface UserDAO {
    User logIn(String login, String password) throws DAOException;

    int add(User user) throws DAOException;
}
