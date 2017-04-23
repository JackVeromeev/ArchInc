package com.netcracker.veromeev.archinc.transaction;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public interface Transaction {
    void execute(Connection connection) throws DAOException;
}
