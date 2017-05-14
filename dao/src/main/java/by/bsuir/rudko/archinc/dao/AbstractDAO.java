package by.bsuir.rudko.archinc.dao;

import by.bsuir.rudko.archinc.entity.Entity;
import by.bsuir.rudko.archinc.entity.factory.EntityFactory;
import by.bsuir.rudko.archinc.dao.util.StatementFiller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

abstract class AbstractDAO<Type extends Entity> {

    AbstractDAO() {}

    public abstract Type findById(int id, Connection connection)
            throws DAOException;
    public abstract List<Type> readAll(Connection connection)
            throws DAOException;
    public abstract void insert(Type val, Connection connection)
            throws DAOException;
    public abstract void update(Type val, Connection connection)
            throws DAOException;
    public abstract void deleteById(int id, Connection connection)
            throws DAOException;



    void executeUpdateQuery(Connection connection, String query,
                            String exceptionMessage,
                            StatementFiller filler)
    throws DAOException {
        try (PreparedStatement statement =
                     connection.prepareStatement(query)) {
            filler.fill(statement);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(exceptionMessage, ex);
        }
    }

    void executeReadQuery(Connection connection, String query,
                          String exceptionMessage,
                          List<Type> resultList,
                          Class entityClass,
                          StatementFiller filler)
    throws DAOException {
        try (PreparedStatement statement =
                     connection.prepareStatement(query)) {
            filler.fill(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultList.add(
                        (Type) EntityFactory.from(resultSet, entityClass)
                );
            }
        } catch (SQLException ex) {
            throw new DAOException(exceptionMessage, ex);
        }
    }
}
