package com.netcracker.veromeev.archinc.dao;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;
import com.netcracker.veromeev.archinc.dao.util.StatementFiller;
import com.netcracker.veromeev.archinc.entity.AbstractEntity;
import com.sun.istack.internal.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<Type extends AbstractEntity> {

    private Connection connection;

    protected String tableName;

    protected String deleteQuery;
    protected String deleteExceptionMessage;


    AbstractDAO(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
        deleteQuery =
                "DELETE FROM " + tableName + " WHERE " +
                        "id_" + tableName + " = ?";
        deleteExceptionMessage = "Can't delete from " + tableName +
                " whth id = ";
    }

    public abstract Type read(int id) throws DAOException;
    public abstract List<Type> readAll() throws DAOException;
    public abstract void insert(Type val) throws DAOException;
    public abstract void update(int id, Type val) throws DAOException;

    /**
     * Delete from the table by id.
     * @param id id of deleted touple
     * @throws DAOException if exception
     */
    public void delete(int id) throws DAOException {
        executeQuery(deleteQuery, deleteExceptionMessage + id,
                statement -> {
                    statement.setInt(1, id);
                    return null;
        });
    }

    protected List<Type> executeQuery(String query, String exceptionMessage,
                                StatementFiller filler)
            throws DAOException {
        List<Type> resultList = null;
        try (PreparedStatement statement =
                     connection.prepareStatement(query)) {
            resultList = filler.fill(statement);
            statement.execute();
        } catch (SQLException ex) {
            throw new DAOException(exceptionMessage, ex);
        }
        return resultList;
    }
}
