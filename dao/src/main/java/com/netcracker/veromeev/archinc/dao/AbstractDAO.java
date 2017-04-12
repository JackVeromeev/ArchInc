package com.netcracker.veromeev.archinc.dao;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;
import com.netcracker.veromeev.archinc.entity.AbstractEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractDAO<Type extends AbstractEntity> {

    private Connection connection;
    protected String tableName;

    protected String UPDATE_QUERY_HEAD;
    protected String UPDATE_QUERY_TAIL;
    protected String DELETE_QUERY;
    protected String INSERT_QUERY;


    AbstractDAO(Connection connection, String tableName) {
        this.setConnection(connection);
        this.tableName = tableName;
        initQueryStrings();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private void initQueryStrings() {
        UPDATE_QUERY_HEAD = "UPDATE " + tableName + " SET ";
        UPDATE_QUERY_TAIL = " WHERE id_" + tableName + " = ?";
        DELETE_QUERY =
                "DELETE FROM " + tableName + " WHERE " +
                "id_" + tableName + " = ?";
        INSERT_QUERY = "INSERT INTO " + tableName + " SET";
    }


    public abstract ArrayList<Type> readAll() throws DAOException;

    public void insert(Type val) throws DAOException {
        String insertQuery = INSERT_QUERY + val.toSQLSet();
        try (PreparedStatement statement =
                     getConnection().prepareStatement(insertQuery)) {
            statement.execute();
        } catch (SQLException ex) {
            throw new DAOException(
                    "Can't insert into " + tableName, ex);
        }
    }

    public abstract Type read(int id) throws DAOException;

    public void update(int id, Type val) throws DAOException {
        String updateQuery = UPDATE_QUERY_HEAD + val.toSQLSet() +
                UPDATE_QUERY_TAIL;
        try (PreparedStatement statement =
                     getConnection().prepareStatement(updateQuery)) {
            statement.execute();
        } catch (SQLException ex) {
            throw new DAOException(
                    "Can't update " + tableName + " by id = " + id, ex);
        }
    }

    public void delete(int id) throws DAOException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            throw new DAOException(
                    "Can't delete " + tableName + " by id = " + id, ex);
        }
    }


}
