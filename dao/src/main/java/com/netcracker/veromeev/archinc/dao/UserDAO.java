package com.netcracker.veromeev.archinc.dao;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;
import com.netcracker.veromeev.archinc.entity.User;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    private static UserDAO instance = null;

    private static final String UPDATE_QUERY =
            "UPDATE User SET Login = ?, Password = ?,"
            + "id_Usertype = ? WHERE id_User = ?;";
    private static final String UPDATE_EXCEPTION_MESSAGE =
            "Can't update User with id = ";

    private static final String INSERT_QUERY =
            "INSERT INTO User SET Login = ?, Password = ?, id_Usertype = ?;";
    private static final String INSERT_EXCEPTION_MESSAGE =
            "Can't insert User with Login = ";

    private static final String SELECT_BY_ID_QUERY =
            "SELECT id_User, id_Usertype, Login, Password FROM User " +
                    "WHERE id_User = ?;";
    private static final String SELECT_BY_ID_EXCEPTION_MESSAGE =
            "Can't find User with id = ";

    private static final String SELECT_BY_LOGIN_PASS_QUERY =
            "SELECT id_User, id_Usertype, Login, Password FROM User " +
                    "WHERE Login=?, Password=?;";
    private static final String SELECT_BY_LOGIN_PASS_EXCEPTION_MESSAGE =
            "Can't find User by Login and Password";

    private static final String SELECT_ALL_USERS_QUERY =
            "SELECT id_User, id_Usertype, Login, Password FROM User";
    private static final String SELECT_ALL_USERS_EXCEPTION_MESSAGE =
            "Can't read all users";

    private static final String DELETE_USER_BT_ID_QUERY =
            "DELETE FROM User WHERE id_User = ?";
    private static final String DELETE_USER_BT_ID_EXCEPTION_MESSAGE =
            "Can't delete User with id = ";

    private UserDAO() {
        super();
    }

    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public User findById(int id, Connection connection) throws DAOException {
        List<User> resultList = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_ID_QUERY,
                SELECT_BY_ID_EXCEPTION_MESSAGE + id,
                resultList, User.class,
                statement -> statement.setInt(1, id)
        );
        if (resultList.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE + id);
        } else {
            return resultList.get(0);
        }
    }

    public User findByNamePass(String login, String password,
                               Connection connection)
            throws DAOException {
        List<User> resultList = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_LOGIN_PASS_QUERY,
                SELECT_BY_LOGIN_PASS_EXCEPTION_MESSAGE,
                resultList, User.class,
                statement -> {
                    statement.setString(1, login);
                    statement.setString(2, password);
        });
        return resultList.get(0);
    }

    @Override
    public List<User> readAll(Connection connection) throws DAOException {
        List<User> resultList = new LinkedList<>();
        executeReadQuery(connection, SELECT_ALL_USERS_QUERY,
                SELECT_ALL_USERS_EXCEPTION_MESSAGE,
                resultList, User.class,
                statement -> {});
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            throw new DAOException("Can't wait 1000 ms", e);
        }
        return resultList;
    }

    @Override
    public void insert(User val, Connection connection) throws DAOException {
        executeUpdateQuery(connection, INSERT_QUERY,
                INSERT_EXCEPTION_MESSAGE + val.getLogin(),
                statement -> {
                    statement.setString(1, val.getLogin());
                    statement.setString(2, val.getPassword());
                    statement.setInt(3, val.getUserType().getId());
        });
    }

    @Override
    public void update(User val, Connection connection) throws DAOException {
        executeUpdateQuery(connection, UPDATE_QUERY,
                UPDATE_EXCEPTION_MESSAGE + val.getId(),
                statement -> {
                    statement.setString(1, val.getLogin());
                    statement.setString(2, val.getPassword());
                    statement.setInt(3, val.getUserType().getId());
                    statement.setInt(4, val.getId());
        });
    }

    @Override
    public void deleteById(int id, Connection connection) throws DAOException {
        executeUpdateQuery(connection, DELETE_USER_BT_ID_QUERY,
                DELETE_USER_BT_ID_EXCEPTION_MESSAGE + id,
                statement -> statement.setInt(1, id));
    }
}
