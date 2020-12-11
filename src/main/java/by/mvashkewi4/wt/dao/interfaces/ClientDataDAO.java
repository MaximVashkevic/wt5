package by.mvashkewi4.wt.dao.interfaces;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.entity.ClientData;

public interface ClientDataDAO {
    int add(ClientData clientData) throws DAOException;
}
