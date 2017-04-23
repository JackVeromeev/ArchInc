package com.netcracker.veromeev.archinc.transaction;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;
import com.netcracker.veromeev.archinc.dbmanager.DBManager;
import com.netcracker.veromeev.archinc.dbmanager.exception.DBManagerException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jack on 20/04/17.
 *
 * @author Jack Veromeyev
 */
public class ReadingTransactionHandler implements TransactionHandler {

    private static ReadingTransactionHandler instance = null;

    private ReadingTransactionHandler() {}

    public static synchronized ReadingTransactionHandler getInstance() {
        if (instance == null) {
            instance = new ReadingTransactionHandler();
        }
        return instance;
    }

    @Override
    public void run(Transaction transaction) throws TransactionException {
        try (Connection connection = DBManager.getInstance().getConnection()) {
            transaction.execute(connection);
        } catch (DBManagerException | SQLException | DAOException e) {
            throw new TransactionException(
                    "While executing reading transaction", e);
        }
    }
}
