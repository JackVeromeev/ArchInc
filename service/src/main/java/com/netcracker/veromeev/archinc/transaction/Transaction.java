package com.netcracker.veromeev.archinc.transaction;

import com.netcracker.veromeev.archinc.dao.DAOException;

import java.sql.Connection;

/**
 * Created by jack on 19/04/17.
 * Transaction interface used as a lambda in TransactionHandler methods
 * @author Jack Veromeyev
 */
public interface Transaction {
    void execute(Connection connection) throws DAOException;
}
