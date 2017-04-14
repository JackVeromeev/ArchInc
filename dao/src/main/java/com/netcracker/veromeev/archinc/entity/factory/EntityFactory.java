package com.netcracker.veromeev.archinc.entity.factory;

import com.netcracker.veromeev.archinc.entity.Entity;
import com.netcracker.veromeev.archinc.entity.User;
import com.netcracker.veromeev.archinc.entity.util.EntityName;
import com.netcracker.veromeev.archinc.enumeration.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EntityFactory {

    public static Entity from(ResultSet resultSet,
                              EntityName name) {
        Entity result = null;
        try {
            switch (name) {
                case USER: result = createUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static User createUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id_User"),
                UserType.getById(resultSet.getInt("id_Usertype")),
                resultSet.getString("Name"),
                resultSet.getString("Password")
        );
    }
}
