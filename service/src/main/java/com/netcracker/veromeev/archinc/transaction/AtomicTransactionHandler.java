package com.netcracker.veromeev.archinc.transaction;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;
import com.netcracker.veromeev.archinc.dbmanager.DBManager;
import com.netcracker.veromeev.archinc.dbmanager.exception.DBManagerException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public class AtomicTransactionHandler implements TransactionHandler {

    private static AtomicTransactionHandler instance = null;

    private static final String exceptionMessage = "in atomic transaction";

    public static synchronized AtomicTransactionHandler getInstance() {
        if (instance == null) {
            instance = new AtomicTransactionHandler();
        }
        return instance;
    }

    private AtomicTransactionHandler() {}

    @Override
    public void run(Transaction transaction) throws TransactionException {
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
                throw new TransactionException(
                        "While rolling back " + exceptionMessage, e1);
            }
            throw new TransactionException(
                    "While executing " + exceptionMessage, e);
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                throw new TransactionException(
                        "While setting autocommit to 'false' "
                                + exceptionMessage, e);
            }
        }
    }
}
