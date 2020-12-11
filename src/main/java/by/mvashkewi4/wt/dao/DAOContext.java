package by.mvashkewi4.wt.dao;

import by.mvashkewi4.wt.dao.connection.ConnectionPool;
import by.mvashkewi4.wt.dao.connection.ConnectionProviderException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DAOContext {
    private static ConnectionPool connectionPool;

    static void init(InputStream inputStream) throws IOException, ConnectionProviderException {
        Properties properties = new Properties();
        properties.load(inputStream);

        connectionPool = new ConnectionPool(properties);
        DAOProvider.init(connectionPool);
    }

    static void stop() throws ConnectionProviderException {
        connectionPool.closeAllConnections();
    }
}
