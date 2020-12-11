package by.mvashkewi4.wt.dao.interfaces;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.entity.PassportData;

public interface PassportDataDAO {
    int add(PassportData passportData) throws DAOException;
}
