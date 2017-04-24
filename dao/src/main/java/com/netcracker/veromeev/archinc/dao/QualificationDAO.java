package com.netcracker.veromeev.archinc.dao;

import com.netcracker.veromeev.archinc.entity.Qualification;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 17/04/17.
 *
 * @author Jack Veromeyev
 */
public class QualificationDAO extends AbstractDAO<Qualification> {

    private static QualificationDAO instance = null;

    private static final String SELECT_ALL_QUERY =
            "SELECT id_Qualification, Qualification FROM Qualification ";
    private static final String SELECT_ALL_EXCEPTION_MESSAGE =
            "Can't read all qualifications";

    private static final String SELECT_BY_ID_QUERY =
            SELECT_ALL_QUERY + " WHERE id_Qualification = ?";
    private static final String SELECT_BY_ID_EXCEPTION_MESSAGE =
            "Can't read a qualification by id = ";

    private static final String INSERT_QUERY =
            "INSERT INTO Qualification " +
                    "SET Qualification=?";
    private static final String INSERT_EXCEPTION_MESSAGE =
            "Can't insert qualification = ";

    private static final String UPDATE_QUERY =
            "UPDATE Qualification " +
                    "SET Qualification=? WHERE id_Qualification=?";
    private static final String UPDATE_EXCEPTION_MESSAGE =
            "Can't update qualification to ";

    private static final String DELETE_QUERY =
            "DELETE FROM Qualification WHERE id_Qualification=?";
    private static final String DELETE_EXCEPTION_MESSAGE =
            "Can't delete from Qualification with id = ";

    private QualificationDAO() {
        super();
    }

    public static synchronized QualificationDAO getInstance() {
        if (instance == null) {
            instance = new QualificationDAO();
        }
        return instance;
    }


    @Override
    public Qualification findById(int id, Connection connection) throws DAOException {
        List<Qualification> qualifications = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_ID_QUERY,
                SELECT_BY_ID_EXCEPTION_MESSAGE + id,
                qualifications, Qualification.class,
                statement -> {}
        );
        if (qualifications.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE + id);
        } else {
            return qualifications.get(0);
        }
    }

    @Override
    public List<Qualification> readAll(Connection connection)
            throws DAOException {
        List<Qualification> qualifications = new LinkedList<>();
        executeReadQuery(connection, SELECT_ALL_QUERY,
                SELECT_ALL_EXCEPTION_MESSAGE,
                qualifications, Qualification.class,
                statement -> {}
        );
        return qualifications;
    }

    @Override
    public void insert(Qualification val, Connection connection)
            throws DAOException {
        executeUpdateQuery(connection, INSERT_QUERY,
                INSERT_EXCEPTION_MESSAGE + val.getQualification(),
                statement -> statement.setString(1, val.getQualification())
        );
    }
    @Override
    public void update(Qualification val, Connection connection)
            throws DAOException {
        executeUpdateQuery(connection, UPDATE_QUERY,
                UPDATE_EXCEPTION_MESSAGE + val.getQualification(),
                statement -> {
                    statement.setString(1, val.getQualification());
                    statement.setInt(2, val.getId());
        });
    }

    @Override
    public void deleteById(int id, Connection connection)
            throws DAOException {
        executeUpdateQuery(connection, DELETE_QUERY,
                DELETE_EXCEPTION_MESSAGE + id,
                statement -> statement.setInt(1, id)
        );
    }
}
