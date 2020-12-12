package by.mvashkewi4.wt.dao.impl;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.dao.connection.ConnectionProvider;
import by.mvashkewi4.wt.dao.connection.ConnectionProviderException;
import by.mvashkewi4.wt.dao.interfaces.CardDAO;
import by.mvashkewi4.wt.entity.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLCardDAO implements CardDAO {
    private static final String DB_COLUMN_ID = "id";
    private static final String DB_COLUMN_ACCOUNT_ID = "account_id";
    private static final String DB_COLUMN_NUMBER_HASH = "number_hash";
    private static final String DB_COLUMN_EXPIRATION_DATE = "expiration_date";
    private static final String DB_COLUMN_LAST_DIGITS = "last_digits";
    private static final String DB_COLUMN_PIN_HASH = "pin_hash";
    private static final String DB_COLUMN_CVV_HASH = "cvv_hash";
    private static final String ADD_CARD_QUERY = "INSERT INTO `cards` (`account_id`, `number_hash`, `expiration_date`, `last_digits`, `pin_hash`, `cvv_hash`) " +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String GET_CARDS_BY_ACCOUNT_ID_QUERY = "SELECT * FROM `cards` WHERE `account_id` = ?;";
    private final ConnectionProvider connectionProvider;

    public SQLCardDAO(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public List<Card> getCards(int accountId) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement getCards = connection.prepareStatement(GET_CARDS_BY_ACCOUNT_ID_QUERY);
            getCards.setInt(1, accountId);
            ResultSet resultSet = getCards.executeQuery();
            List<Card> result = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    result.add(new Card(
                            resultSet.getInt(DB_COLUMN_ID),
                            resultSet.getInt(DB_COLUMN_ACCOUNT_ID),
                            resultSet.getString(DB_COLUMN_NUMBER_HASH),
                            resultSet.getDate(DB_COLUMN_EXPIRATION_DATE),
                            resultSet.getShort(DB_COLUMN_LAST_DIGITS),
                            resultSet.getString(DB_COLUMN_PIN_HASH),
                            resultSet.getString(DB_COLUMN_CVV_HASH)
                    ));
                }
                resultSet.close();
            }
            getCards.close();
            return result;
        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void addCard(Card card) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement addStatement = connection.prepareStatement(ADD_CARD_QUERY);
            addStatement.setInt(1, card.getAccountId());
            addStatement.setString(2, card.getNumberHash());
            addStatement.setDate(3, card.getExpirationDate());
            addStatement.setShort(4, card.getLastDigits());
            addStatement.setString(5, card.getPinHash());
            addStatement.setString(6, card.getCvvHash());
            addStatement.executeUpdate();
            addStatement.close();
        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
    }
}
