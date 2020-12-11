package by.mvashkewi4.wt.dao.interfaces;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.entity.Address;

public interface AddressDAO {
    int add(Address address) throws DAOException;
}
