package by.bsuir.rudko.archinc.entity.factory;

import by.bsuir.rudko.archinc.entity.*;
import by.bsuir.rudko.archinc.enumeration.Gender;
import by.bsuir.rudko.archinc.enumeration.OrderState;
import by.bsuir.rudko.archinc.enumeration.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EntityFactory {

    public static Entity from(ResultSet resultSet,
                              Class name) throws SQLException {
        Entity result = null;
        if(name == User.class) {
            result = userFrom(resultSet);
        } else if (name == CustomerOrder.class) {
            result = custerOrderFrom(resultSet);
        } else if (name == Qualification.class) {
            result = qualificationFrom(resultSet);
        } else if (name == OrderedMan.class) {
            result = orderedMenFrom(resultSet);
        } else if (name == Town.class) {
            result = townFrom(resultSet);
        } else if (name == Region.class) {
            result = regionFrom(resultSet);
        } else if (name == Country.class) {
            result = countryFrom(resultSet);
        } else if (name == Address.class) {
            result = addressFrom(resultSet);
        } else if (name == Employee.class) {
            result = employeeFrom(resultSet);
        }
        return result;
    }

    private static OrderedMan orderedMenFrom(ResultSet resultSet)
            throws SQLException {
        return new OrderedMan(
                resultSet.getInt("id_OrderedMan"),
                resultSet.getInt("id_Order"),
                resultSet.getInt("id_Qualification"),
                resultSet.getInt("Amount")
        );
    }

    private static User userFrom(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id_User"),
                UserType.getById(resultSet.getInt("id_Usertype")),
                resultSet.getString("Login"),
                resultSet.getString("Password")
        );
    }


    private static CustomerOrder custerOrderFrom(ResultSet resultSet)
            throws SQLException {
        return new CustomerOrder(
                resultSet.getInt("id_Order"),
                resultSet.getString("Title"),
                resultSet.getString("Description"),
                resultSet.getInt("id_User"),
                OrderState.of(resultSet.getString("OrderState"))
        );
    }

    private static Qualification qualificationFrom(ResultSet resultSet)
            throws  SQLException {
        return new Qualification(
                resultSet.getInt("id_Qualification"),
                resultSet.getString("Qualification")
        );
    }

    private static Town townFrom(ResultSet resultSet)
            throws  SQLException {
        return new Town(
                resultSet.getInt("id_Town"),
                resultSet.getString("Town")
        );
    }

    private static Region regionFrom(ResultSet resultSet)
            throws  SQLException {
        return new Region(
                resultSet.getInt("id_Region"),
                resultSet.getString("Region")
        );
    }

    private static Country countryFrom(ResultSet resultSet)
            throws  SQLException {
        return new Country(
                resultSet.getInt("id_Country"),
                resultSet.getString("Country")
        );
    }

    private static Address addressFrom(ResultSet resultSet)
            throws SQLException {
        return new Address(
                resultSet.getInt("id_Address"),
                resultSet.getInt("id_Town"),
                resultSet.getInt("id_Country"),
                resultSet.getInt("id_Region"),
                resultSet.getString("Street"),
                resultSet.getInt("House")
        );
    }

    private static Employee employeeFrom(ResultSet resultSet)
            throws SQLException {
        return new Employee(
                resultSet.getInt("id_Employee"),
                resultSet.getString("FirstName"),
                resultSet.getString("SecondName"),
                resultSet.getString("LastName"),
                resultSet.getInt("Age"),
                Gender.of(resultSet.getString("Gender")),
                resultSet.getInt("ExperienceInYears"),
                resultSet.getString("Phone_number"),
                resultSet.getInt("SalaryPerDay"),
                resultSet.getInt("id_Qualification"),
                resultSet.getInt("id_Education"),
                resultSet.getInt("id_Address"),
                resultSet.getInt("id_Order")
        );

    }
}
