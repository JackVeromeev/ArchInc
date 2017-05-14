package by.bsuir.rudko.archinc.listener;

import by.bsuir.rudko.archinc.dbmanager.DBManagerException;
import by.bsuir.rudko.archinc.dbmanager.DBManager;
import by.bsuir.rudko.archinc.enumeration.EnumerationInitializer;
import by.bsuir.rudko.archinc.enumeration.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

public class ApplicationListener implements ServletContextListener{

    Logger logger = LoggerFactory.getLogger(ApplicationListener.class);

    // Public constructor is required by servlet spec
    public ApplicationListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        Connection connection;
        try {
            connection = DBManager.getInstance().getConnection();
            EnumerationInitializer.initialize(connection);
        } catch (DBManagerException | SQLException e) {
            logger.error("Can't initialize enums", e);
        }
        UserType.NA.setId(0);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DBManager.getInstance().deregisterDrivers();
        } catch (DBManagerException e) {
            logger.error("Can't deregister all drivers for database", e);
        }
    }
}
