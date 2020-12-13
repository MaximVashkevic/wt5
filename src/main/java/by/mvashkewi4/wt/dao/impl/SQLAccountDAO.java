package by.mvashkewi4.wt.dao.impl;

import by.mvashkewi4.wt.dao.DAOException;
import by.mvashkewi4.wt.dao.connection.ConnectionProvider;
import by.mvashkewi4.wt.dao.connection.ConnectionProviderException;
import by.mvashkewi4.wt.dao.interfaces.AccountDAO;
import by.mvashkewi4.wt.entity.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLAccountDAO implements AccountDAO {
    private static final String ADD_ACCOUNT_QUERY =
            "INSERT INTO `accounts` (`user_id`, `currency_id`, `account_number`, `is_locked`, `amount`) " +
                    "VALUES (?, ?, ?, ?, ?);";
    private static final String GET_ACCOUNTS_BY_USER_ID_QUERY = "SELECT * FROM `accounts` WHERE `user_id` = ?;";
    private static final String GET_ACCOUNT_BY_ACCOUNT_ID_QUERY = "SELECT * FROM `accounts` WHERE `account_id` = ?;";
    private static final String CHANGE_AMOUNT_QUERY = "UPDATE `accounts` SET `amount` = ? WHERE `id` = ?;";
    private static final String LOCK_ACCOUNT_QUERY = "UPDATE `accounts` SET `is_locked` = true WHERE `id` = ?;";
    private static final String DB_COLUMN_ID = "id";
    private static final String DB_COLUMN_USER_ID = "user_id";
    private static final String DB_COLUMN_CURRENCY_ID = "currency_id";
    private static final String DB_COLUMN_ACCOUNT_NUMBER = "account_number";
    private static final String DB_COLUMN_IS_LOCKED = "is_locked";
    private static final String DB_COLUMN_AMOUNT = "amount";
    private final ConnectionProvider connectionProvider;

    public SQLAccountDAO(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public Account getAccount(int accountId) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement getAccountsStatemen = connection.prepareStatement(GET_ACCOUNTS_BY_USER_ID_QUERY);
            getAccountsStatemen.setInt(1, accountId);
            ResultSet resultSet = getAccountsStatemen.executeQuery();
            Account result = null;
            if (resultSet != null && resultSet.next()) {
                result = new Account(
                        resultSet.getInt(DB_COLUMN_ID),
                        resultSet.getInt(DB_COLUMN_USER_ID),
                        resultSet.getInt(DB_COLUMN_CURRENCY_ID),
                        resultSet.getString(DB_COLUMN_ACCOUNT_NUMBER),
                        resultSet.getBoolean(DB_COLUMN_IS_LOCKED),
                        resultSet.getBigDecimal(DB_COLUMN_AMOUNT)
                );
                resultSet.close();
            }
            getAccountsStatemen.close();

            return result;

        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Account> getUserAccounts(int userId) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement getAccountsStatement = connection.prepareStatement(GET_ACCOUNTS_BY_USER_ID_QUERY);
            getAccountsStatement.setInt(1, userId);
            ResultSet resultSet = getAccountsStatement.executeQuery();
            List<Account> result = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next()) {
                    result.add(new Account(
                            resultSet.getInt(DB_COLUMN_ID),
                            resultSet.getInt(DB_COLUMN_USER_ID),
                            resultSet.getInt(DB_COLUMN_CURRENCY_ID),
                            resultSet.getString(DB_COLUMN_ACCOUNT_NUMBER),
                            resultSet.getBoolean(DB_COLUMN_IS_LOCKED),
                            resultSet.getBigDecimal(DB_COLUMN_AMOUNT)
                    ));
                }
                resultSet.close();
            }
            getAccountsStatement.close();

            return result;

        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void changeAmount(Account account, BigDecimal newAmount) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement changeAmountStatement = connection.prepareStatement(CHANGE_AMOUNT_QUERY);
            changeAmountStatement.setInt(1, account.getId());
            changeAmountStatement.setBigDecimal(2, newAmount);
            changeAmountStatement.executeUpdate();
            changeAmountStatement.close();
        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void addAccount(Account account) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement addAccountStatement = connection.prepareStatement(ADD_ACCOUNT_QUERY);
            addAccountStatement.setInt(1, account.getUserId());
            addAccountStatement.setInt(2, account.getCurrencyId());
            addAccountStatement.setString(3, account.getAccountNumber());
            addAccountStatement.setBoolean(4, account.getIsLocked());
            addAccountStatement.setBigDecimal(5, account.getAmount());
            addAccountStatement.executeUpdate();
            addAccountStatement.close();
        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void lockAccount(Account account) throws DAOException {
        try (Connection connection = connectionProvider.getConnection()) {
            PreparedStatement lockAccount = connection.prepareStatement(LOCK_ACCOUNT_QUERY);
            lockAccount.setInt(1, account.getId());
            lockAccount.executeUpdate();
            lockAccount.close();
        } catch (SQLException | ConnectionProviderException e) {
            throw new DAOException(e);
        }
    }
}
