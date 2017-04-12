package com.netcracker.veromeev.archinc.dbmanager;


import com.netcracker.veromeev.archinc.dbmanager.exception.DBManagerException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class DBManager {

    private DataSource dataSource;
    private static DBManager instance;

    private DBManager() throws DBManagerException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource =
                    (DataSource) envContext.lookup("jdbc/arch_inc");
        } catch (NamingException ex) {
            throw new DBManagerException("Can't get DataSource", ex);
        }
    }

    public static synchronized DBManager getInstance()
            throws DBManagerException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() throws DBManagerException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException ex) {
            throw new DBManagerException("Can't get connection", ex);
        }
        return connection;
    }

    public void deregisterDrivers() throws DBManagerException {
        Enumeration<Driver> driverEnumeration = DriverManager.getDrivers();
        while (driverEnumeration.hasMoreElements()) {
            Driver driver = driverEnumeration.nextElement();
            ClassLoader driverClassLoader = driver.getClass().getClassLoader();
            ClassLoader thisClassLoader = this.getClass().getClassLoader();
            if (driverClassLoader != null && thisClassLoader != null) {
                try {
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    throw new DBManagerException("Can't deregister driver", ex);
                }
            }
        }
    }
}
