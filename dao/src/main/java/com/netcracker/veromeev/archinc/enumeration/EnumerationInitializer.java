package com.netcracker.veromeev.archinc.enumeration;

import com.netcracker.veromeev.archinc.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jack on 12/04/17.
 *
 * @author Jack Veromeyev
 */
public class EnumerationInitializer {

    private static final String USER_TYPE_QUERY =
            "SELECT (id_Usertype, Type) FROM Usertype";

    public static void initialize(Connection connection) throws SQLException {
        initializeUserType(connection);
    }

    private static void initializeUserType(Connection connection)
            throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement(USER_TYPE_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String type = resultSet.getString(2);
                UserType.valueOf(type.toUpperCase()).setId(id);
            }
        } catch (SQLException ex) {
            throw new SQLException(
                    "Can't initialize id-s in UserType enumeration", ex);
        }
    }
}
