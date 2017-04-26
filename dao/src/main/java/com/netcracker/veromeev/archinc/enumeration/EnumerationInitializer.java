package com.netcracker.veromeev.archinc.enumeration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumerationInitializer {

    private static final String USER_TYPE_QUERY =
            "SELECT id_Usertype, Type FROM Usertype";

    private static final String EDUCATION_QUERY =
            "SELECT id_Education, Education FROM Education";

    public static void initialize(Connection connection) throws SQLException {
        initializeUserType(connection);
        initializeEducation(connection);
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

    private static void initializeEducation(Connection connection)
            throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement(EDUCATION_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String type = resultSet.getString(2);
                Education.valueOf(type.toUpperCase()).setId(id);
            }
        } catch (SQLException ex) {
            throw new SQLException(
                    "Can't initialize id-s in Education enumeration", ex);
        }
    }
}
