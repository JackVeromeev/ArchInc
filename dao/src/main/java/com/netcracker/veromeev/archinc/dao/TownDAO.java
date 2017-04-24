package com.netcracker.veromeev.archinc.dao;

import com.netcracker.veromeev.archinc.entity.Town;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class TownDAO extends AbstractDAO<Town> {

    private static TownDAO instance = null;

    private static final String SELECT_ALL_QUERY =
            "SELECT id_Town, Town FROM Town ";
    private static final String SELECT_ALL_EXCEPTION_MESSAGE =
            "Can't read all towns";

    private static final String SELECT_BY_ID_QUERY =
            SELECT_ALL_QUERY + " WHERE id_Town=?";
    private static final String SELECT_BY_ID_EXCEPTION_MESSAGE =
            "Can't read a town by name = ";

    private static final String SELECT_BY_NAME_QUERY =
            SELECT_ALL_QUERY + " WHERE Town=?";
    private static final String SELECT_BY_NAME_EXCEPTION_MESSAGE =
            "Can't read a town by name = ";

    private static final String INSERT_QUERY =
            "INSERT INTO Town SET Town=?";
    private static final String INSERT_EXCEPTION_MESSAGE =
            "Can't insert town = ";

    private static final String UPDATE_QUERY =
            "UPDATE Town SET Town=? WHERE id_Town=?";
    private static final String UPDATE_EXCEPTION_MESSAGE =
            "Can't update town to ";

    private static final String DELETE_QUERY =
            "DELETE FROM Town WHERE id_Town=?";
    private static final String DELETE_EXCEPTION_MESSAGE =
            "Can't delete from Town with id = ";

    private TownDAO() {
        super();
    }

    public static synchronized TownDAO getInstance() {
        return instance == null ? instance = new TownDAO() : instance;
    }

    @Override
    public Town findById(int id, Connection connection) throws DAOException {
        List<Town> towns = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_ID_QUERY,
                SELECT_BY_ID_EXCEPTION_MESSAGE + id,
                towns, Town.class,
                statement -> statement.setInt(1, id));
        if (towns.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE + id);
        }
        return towns.get(0);
    }

    public Town findByName(String name, Connection connection)
            throws DAOException {
        List<Town> towns = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_NAME_QUERY,
                SELECT_BY_NAME_EXCEPTION_MESSAGE + name,
                towns, Town.class,
                statement -> statement.setString(1, name));
        if (towns.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE + name);
        }
        return towns.get(0);
    }

    @Override
    public List<Town> readAll(Connection connection) throws DAOException {
        List<Town> towns = new LinkedList<>();
        executeReadQuery(connection, SELECT_ALL_QUERY,
                SELECT_ALL_EXCEPTION_MESSAGE,
                towns, Town.class,
                statement -> {}
        );
        return towns;
    }

    @Override
    public void insert(Town val, Connection connection) throws DAOException {
        executeUpdateQuery(connection, INSERT_QUERY,
                INSERT_EXCEPTION_MESSAGE + val.getTown(),
                statement -> statement.setString(1, val.getTown())
        );
    }

    @Override
    public void update(Town val, Connection connection) throws DAOException {
        executeUpdateQuery(connection, UPDATE_QUERY,
                UPDATE_EXCEPTION_MESSAGE + val.getTown(),
                statement -> {
                    statement.setString(1, val.getTown());
                    statement.setInt(2, val.getId());
        });
    }

    @Override
    public void deleteById(int id, Connection connection) throws DAOException {
        executeUpdateQuery(connection, DELETE_QUERY,
                DELETE_EXCEPTION_MESSAGE + id,
                statement -> statement.setInt(1, id)
        );
    }
}
