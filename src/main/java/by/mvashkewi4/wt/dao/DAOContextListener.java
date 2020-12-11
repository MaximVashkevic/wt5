package by.mvashkewi4.wt.dao;

import by.mvashkewi4.wt.dao.connection.ConnectionProviderException;
import by.mvashkewi4.wt.utils.ResourceUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DAOContextListener implements ServletContextListener {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DAOContextListener.class);
    private static final String DB_PROPERTIES_FILE_NAME = "db.properties";

    public void contextInitialized(ServletContextEvent sce) {
        log.info("Initializing DAO layer");
        try (InputStream dbPropertiesStream = ResourceUtils.getResourceInputStream(DB_PROPERTIES_FILE_NAME)) {
            DAOContext.init(dbPropertiesStream);
        } catch (FileNotFoundException e) {
            log.error("File {} not found", DB_PROPERTIES_FILE_NAME);
        } catch (IOException e) {
            log.error("Exception while working with file", e);
        } catch (ConnectionProviderException e) {
            log.error("Connection provider exception", e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Uninitializing DAO layer");
        try {
            DAOContext.stop();
        } catch (ConnectionProviderException e) {
            log.error("Connection provider exception", e);
        }
    }
}
