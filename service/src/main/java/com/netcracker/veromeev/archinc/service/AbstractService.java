package com.netcracker.veromeev.archinc.service;

import com.netcracker.veromeev.archinc.dao.DAOException;
import com.netcracker.veromeev.archinc.dbmanager.DBManager;
import com.netcracker.veromeev.archinc.dbmanager.DBManagerException;
import com.netcracker.veromeev.archinc.transaction.Transaction;
import com.netcracker.veromeev.archinc.transaction.TransactionException;
import com.netcracker.veromeev.archinc.transaction.TransactionHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jack on 23/04/17.
 *
 * @author Jack Veromeyev
 */
public abstract class AbstractService implements TransactionHandler {

    @Override
    public void runAtomicTransaction(String methodName,
                                     Transaction transaction)
            throws ServiceException {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            transaction.execute(connection);

            connection.commit();
        } catch (DBManagerException | DAOException | SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                throw new ServiceException(
                        "While rolling back in " + methodName, e1);
            }
            throw new ServiceException(
                    "While executing atomic transaction in "
                    + methodName, e);
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                throw new ServiceException(
                        "While setting autocommit to 'false' in "
                                + methodName, e);
            }
        }
    }

    @Override
    public void runReadingTransaction(String methodName,
                                      Transaction transaction)
            throws ServiceException {
        try (Connection connection = DBManager.getInstance().getConnection()) {
            transaction.execute(connection);
        } catch (DBManagerException | SQLException | DAOException e) {
            throw new ServiceException(
                    "While executing reading transaction in "
                    + methodName, e);
        }
    }

}


