package by.mvashkewi4.wt.dao.interfaces;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDAO {
    List<Account> getUserAccounts(int userId) throws DAOException;

    void changeAmount(Account account, BigDecimal newAmount) throws DAOException;

    void addAccount(Account account) throws DAOException;

    void lockAccount(Account account) throws DAOException;

}
