package com.netcracker.veromeev.archinc.dao;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;
import com.netcracker.veromeev.archinc.dao.util.StatementFiller;
import com.netcracker.veromeev.archinc.entity.Entity;
import com.netcracker.veromeev.archinc.entity.factory.EntityFactory;
import com.netcracker.veromeev.archinc.entity.util.EntityName;
import com.sun.istack.internal.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<Type extends Entity> {

    private Connection connection;

    private String tableName;


    AbstractDAO(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    public abstract Type read(int id) throws DAOException;
    public abstract List<Type> readAll() throws DAOException;
    public abstract void insert(Type val) throws DAOException;
    public abstract void update(Type val) throws DAOException;
    public abstract void delete(int id) throws DAOException;

    protected void executeQuery(String query, String exceptionMessage,
                                @Nullable List<Type> resultList,
                                @Nullable EntityName entityName,
                                StatementFiller filler)
                                throws DAOException {
        try (PreparedStatement statement =
                     connection.prepareStatement(query)) {
            filler.fill(statement);
            if (resultList == null) {
                statement.executeUpdate();
            } else {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultList.add(
                            (Type) EntityFactory.from(resultSet, entityName));
                }
            }
        } catch (SQLException ex) {
            throw new DAOException(exceptionMessage, ex);
        }
    }
}
