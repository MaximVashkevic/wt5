package by.mvashkewi4.wt.dao.connection;

import java.sql.Connection;

public interface ConnectionProvider {
    Connection getConnection() throws ConnectionProviderException;
}