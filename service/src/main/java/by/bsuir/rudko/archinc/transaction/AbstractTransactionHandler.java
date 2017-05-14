package by.bsuir.rudko.archinc.transaction;

import by.bsuir.rudko.archinc.dao.DAOException;
import by.bsuir.rudko.archinc.dbmanager.DBManager;
import by.bsuir.rudko.archinc.dbmanager.DBManagerException;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractTransactionHandler implements TransactionHandler {

    @Override
    public void runAtomicTransaction(String methodName,
                                     Transaction transaction)
            throws TransactionException {
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
                        "While rolling back in " + methodName, e1);
            }
            throw new TransactionException(
                    "While executing atomic transaction in "
                            + methodName, e);
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                throw new TransactionException(
                        "While setting autocommit to 'true' in "
                                + methodName, e);
            }
        }
    }

    @Override
    public void runReadingTransaction(String methodName,
                                      Transaction transaction)
            throws TransactionException {
        try (Connection connection = DBManager.getInstance().getConnection()) {
            transaction.execute(connection);
        } catch (DBManagerException | SQLException | DAOException e) {
            throw new TransactionException(
                    "While executing reading transaction in "
                            + methodName, e);
        }
    }

}
