package by.mvashkewi4.wt.dao.interfaces;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.entity.Transaction;

import java.util.List;

public interface TransactionDAO {
    List<Transaction> getTransactions(int cardId) throws DAOException;

    void addTransaction(Transaction transaction) throws DAOException;
}
