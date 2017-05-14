package by.bsuir.rudko.archinc.dao;

import by.bsuir.rudko.archinc.entity.Country;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public class CountryDAO extends AbstractDAO<Country> {

    private static CountryDAO instance = null;

    private static final String SELECT_ALL_QUERY =
            "SELECT id_Country, Country FROM Country ";
    private static final String SELECT_ALL_EXCEPTION_MESSAGE =
            "Can't read all countries";

    private static final String SELECT_BY_ID_QUERY =
            SELECT_ALL_QUERY + " WHERE id_Country=?";
    private static final String SELECT_BY_ID_EXCEPTION_MESSAGE =
            "Can't read a country by name = ";

    private static final String SELECT_BY_VALUE_QUERY =
            SELECT_ALL_QUERY + " WHERE Country=?";
    private static final String SELECT_BY_VALUE_EXCEPTION_MESSAGE =
            "Can't read a country by name = ";

    private static final String INSERT_QUERY =
            "INSERT INTO Country SET Country=?";
    private static final String INSERT_EXCEPTION_MESSAGE =
            "Can't insert country = ";

    private static final String UPDATE_QUERY =
            "UPDATE Country SET Country=? WHERE id_Country=?";
    private static final String UPDATE_EXCEPTION_MESSAGE =
            "Can't update country to ";

    private static final String DELETE_QUERY =
            "DELETE FROM Country WHERE id_Country=?";
    private static final String DELETE_EXCEPTION_MESSAGE =
            "Can't delete from Country with id = ";

    private CountryDAO() {
        super();
    }

    public static synchronized CountryDAO getInstance() {
        if (instance == null) {
            instance = new CountryDAO();
        }
        return instance;
    }

    @Override
    public Country findById(int id, Connection connection) throws DAOException {
        List<Country> countries = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_ID_QUERY,
                SELECT_BY_ID_EXCEPTION_MESSAGE + id,
                countries, Country.class,
                statement -> statement.setInt(1, id));
        if (countries.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE + id);
        }
        return countries.get(0);
    }

    public Country findByName(String name, Connection connection)
            throws DAOException {
        List<Country> countries = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_VALUE_QUERY,
                SELECT_BY_VALUE_EXCEPTION_MESSAGE + name,
                countries, Country.class,
                statement -> statement.setString(1, name));
        if (countries.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE + name);
        }
        return countries.get(0);
    }

    @Override
    public List<Country> readAll(Connection connection) throws DAOException {
        List<Country> countries = new LinkedList<>();
        executeReadQuery(connection, SELECT_ALL_QUERY,
                SELECT_ALL_EXCEPTION_MESSAGE,
                countries, Country.class,
                statement -> {}
        );
        return countries;
    }

    @Override
    public void insert(Country val, Connection connection) throws DAOException {
        executeUpdateQuery(connection, INSERT_QUERY,
                INSERT_EXCEPTION_MESSAGE + val.getCountry(),
                statement -> statement.setString(1, val.getCountry())
        );
    }

    @Override
    public void update(Country val, Connection connection) throws DAOException {
        executeUpdateQuery(connection, UPDATE_QUERY,
                UPDATE_EXCEPTION_MESSAGE + val.getCountry(),
                statement -> {
                    statement.setString(1, val.getCountry());
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
