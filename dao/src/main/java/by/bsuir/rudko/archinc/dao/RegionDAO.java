package by.bsuir.rudko.archinc.dao;

import by.bsuir.rudko.archinc.entity.Region;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public class RegionDAO extends AbstractDAO<Region> {

    private static RegionDAO instance = null;

    private static final String SELECT_ALL_QUERY =
            "SELECT id_Region, Region FROM Region ";
    private static final String SELECT_ALL_EXCEPTION_MESSAGE =
            "Can't read all regions";

    private static final String SELECT_BY_ID_QUERY =
            SELECT_ALL_QUERY + " WHERE id_Region=?";
    private static final String SELECT_BY_ID_EXCEPTION_MESSAGE =
            "Can't read a region by name = ";

    private static final String SELECT_BY_NAME_QUERY =
            SELECT_ALL_QUERY + " WHERE Region=?";
    private static final String SELECT_BY_NAME_EXCEPTION_MESSAGE =
            "Can't read a region by name = ";

    private static final String INSERT_QUERY =
            "INSERT INTO Region SET Region=?";
    private static final String INSERT_EXCEPTION_MESSAGE =
            "Can't insert region = ";

    private static final String UPDATE_QUERY =
            "UPDATE Region SET Region=? WHERE id_Region=?";
    private static final String UPDATE_EXCEPTION_MESSAGE =
            "Can't update region to ";

    private static final String DELETE_QUERY =
            "DELETE FROM Region WHERE id_Region=?";
    private static final String DELETE_EXCEPTION_MESSAGE =
            "Can't delete from Region with id = ";

    private RegionDAO() {
        super();
    }

    public static synchronized RegionDAO getInstance() {
        if (instance == null) {
            instance = new RegionDAO();
        }
        return instance;
    }

    @Override
    public Region findById(int id, Connection connection) throws DAOException {
        List<Region> regions = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_ID_QUERY,
                SELECT_BY_ID_EXCEPTION_MESSAGE + id,
                regions, Region.class,
                statement -> statement.setInt(1, id));
        if (regions.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE + id);
        }
        return regions.get(0);
    }

    public Region findByName(String name, Connection connection)
            throws DAOException {
        List<Region> regions = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_NAME_QUERY,
                SELECT_BY_NAME_EXCEPTION_MESSAGE + name,
                regions, Region.class,
                statement -> statement.setString(1, name));
        if (regions.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE + name);
        }
        return regions.get(0);
    }

    @Override
    public List<Region> readAll(Connection connection) throws DAOException {
        List<Region> regions = new LinkedList<>();
        executeReadQuery(connection, SELECT_ALL_QUERY,
                SELECT_ALL_EXCEPTION_MESSAGE,
                regions, Region.class,
                statement -> {}
        );
        return regions;
    }

    @Override
    public void insert(Region val, Connection connection) throws DAOException {
        executeUpdateQuery(connection, INSERT_QUERY,
                INSERT_EXCEPTION_MESSAGE + val.getRegion(),
                statement -> statement.setString(1, val.getRegion())
        );
    }

    @Override
    public void update(Region val, Connection connection) throws DAOException {
        executeUpdateQuery(connection, UPDATE_QUERY,
                UPDATE_EXCEPTION_MESSAGE + val.getRegion(),
                statement -> {
                    statement.setString(1, val.getRegion());
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
