package com.netcracker.veromeev.archinc.dao;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;
import com.netcracker.veromeev.archinc.entity.Address;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public class AddressDAO extends AbstractDAO<Address> {

    private static AddressDAO instance = null;

    private static final String SELECT_ALL_QUERY =
            "SELECT " +
            "id_Address, id_Country, id_Region, id_Town, Street, House " +
            " FROM Address ";
    private static final String SELECT_ALL_EXCEPTION_MESSAGE =
            "Can't read all addresses";

    private static final String SELECT_BY_ID_QUERY =
            SELECT_ALL_QUERY + " WHERE id_Address=?";
    private static final String SELECT_BY_ID_EXCEPTION_MESSAGE =
            "Can't find an address with id = ";

    private static final String SELECT_BY_VALUE_QUERY =
            SELECT_ALL_QUERY + " WHERE ";
    private static final String SELECT_BY_VALUE_EXCEPTION_MESSAGE =
            "Can't find an address";

    private static final String INSERT_QUERY =
            "INSERT INTO Address SET " +
            "id_Country=?, id_Region=?, id_Town=?, Street=?, House=?";
    private static final String INSERT_EXCEPTION_MESSAGE =
            "Can't insert address";

    private static final String UPDATE_QUERY =
            "UPDATE Address SET " +
            "id_Country=?, id_Region=?, id_Town=?, Street=?, House=? " +
            "WHERE id_Address=?";
    private static final String UPDATE_EXCEPTION_MESSAGE =
            "Can't update address";

    private static final String DELETE_QUERY =
            "DELETE FROM Address WHERE id_Address=?";
    private static final String DELETE_EXCEPTION_MESSAGE =
            "Can't delete from Address with id = ";

    private AddressDAO() {
        super();
    }

    public static synchronized AddressDAO getInstance() {
        return instance == null ? instance = new AddressDAO() : instance;
    }

    @Override
    public Address findById(int id, Connection connection)
            throws DAOException {
        List<Address> addresses = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_ID_QUERY,
                SELECT_BY_ID_EXCEPTION_MESSAGE + id,
                addresses, Address.class,
                statement -> statement.setInt(1, id)
        );
        if (addresses.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE + id);
        }
        return addresses.get(0);
    }

    @Override
    public List<Address> readAll(Connection connection)
            throws DAOException {
        List<Address> addresses = new LinkedList<>();
        executeReadQuery(connection, SELECT_ALL_QUERY,
                SELECT_ALL_EXCEPTION_MESSAGE,
                addresses, Address.class,
                statement -> {}
        );
        return addresses;
    }

    @Override
    public void insert(Address val, Connection connection)
            throws DAOException {
        executeUpdateQuery(connection, INSERT_QUERY,
                INSERT_EXCEPTION_MESSAGE,
                statement ->  {
                    statement.setInt(1, val.getCountryId());
                    statement.setInt(2, val.getRegionId());
                    statement.setInt(3, val.getTownId());
                    statement.setString(4, val.getStreet());
                    statement.setInt(5, val.getHouse());
        });
    }

    @Override
    public void update(Address val, Connection connection)
            throws DAOException {
        executeUpdateQuery(connection, UPDATE_QUERY,
                UPDATE_EXCEPTION_MESSAGE + val.getId(),
                statement -> {
                    statement.setInt(1, val.getCountryId());
                    statement.setInt(2, val.getRegionId());
                    statement.setInt(3, val.getTownId());
                    statement.setString(4, val.getStreet());
                    statement.setInt(5, val.getHouse());
                    statement.setInt(6, val.getId());
        });
    }

    @Override
    public void deleteById(int id, Connection connection)
            throws DAOException {
        executeUpdateQuery(connection, DELETE_QUERY,
                DELETE_EXCEPTION_MESSAGE + id,
                statement -> statement.setInt(1, id));
    }


}
