package by.mvashkewi4.wt.dao.impl;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.dao.connection.ConnectionProvider;
import by.mvashkewi4.wt.dao.connection.ConnectionProviderException;
import by.mvashkewi4.wt.dao.interfaces.TransactionDAO;
import by.mvashkewi4.wt.entity.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLTransactionDAO implements TransactionDAO {
    public static final String ADD_TRANSACTION_QUERY = "INSERT INTO `transactions` (`card_id`, `amount`) VALUES (?, ?);";
    private static final String GET_TRANSACTIONS_BY_CARD_ID_QUERY = "SELECT * FROM `transactions` WHERE `card_id` = ?;";

    private static final String DB_COLUMN_ID = "id";
    private static final String DB_COLUMN_CARD_ID = "card_id";
    private static final String DB_COLUMN_AMOUNT = "amount";
    private final ConnectionProvider connectionProvider;

    public SQLTransactionDAO(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public List<Transaction> getTransactions(int cardId) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement getTransactions = connection.prepareStatement(GET_TRANSACTIONS_BY_CARD_ID_QUERY);
            getTransactions.setInt(1, cardId);
            ResultSet resultSet = getTransactions.executeQuery();
            List<Transaction> result = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    result.add(new Transaction(
                            resultSet.getInt(DB_COLUMN_ID),
                            resultSet.getInt(DB_COLUMN_CARD_ID),
                            resultSet.getBigDecimal(DB_COLUMN_AMOUNT)
                    ));
                }
                resultSet.close();
            }
            getTransactions.close();
            return result;
        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void addTransaction(Transaction transaction) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement addStatement = connection.prepareStatement(ADD_TRANSACTION_QUERY);
            addStatement.setInt(1, transaction.getCardId());
            addStatement.setBigDecimal(2, transaction.getAmount());
            addStatement.executeUpdate();
            addStatement.close();
        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
    }
}
