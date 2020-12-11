package by.mvashkewi4.wt.dao.connection;

public class ConnectionProviderException extends Exception {
    public ConnectionProviderException(Exception associatedException) {
        super(associatedException);
    }
}