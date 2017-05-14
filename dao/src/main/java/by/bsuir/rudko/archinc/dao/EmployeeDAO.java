package by.bsuir.rudko.archinc.dao;

import by.bsuir.rudko.archinc.entity.Employee;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public class EmployeeDAO extends AbstractDAO<Employee> {

    private static EmployeeDAO instance = null;

    private static final String SELECT_ALL_QUERY =
            "SELECT id_Employee, FirstName, SecondName, LastName, Age, " +
            "Gender, id_Qualification, ExperienceInYears, id_Education, " +
            "Phone_number, id_Address, SalaryPerDay, id_Order FROM Employee";
    private static final String SELECT_ALL_EXCEPTION_MESSAGE =
            "Can't open all employees";

    private static final String SELECT_BY_ID_QUERY =
            SELECT_ALL_QUERY + " WHERE id_Employee=?";
    private static final String SELECT_BY_ID_EXCEPTION_MESSAGE =
            "Can't find an employee with id = ";

    private static final String INSERT_QUERY =
            "INSERT INTO Employee SET FirstName=?, SecondName=?, LastName=?," +
            " Age=?, Gender=?, id_Qualification=?, ExperienceInYears=?, " +
            "id_Education=?, Phone_number=?, id_Address=?, SalaryPerDay=?, " +
            "id_Order=?";
    private static final String INSERT_EXCEPTION_MESSAGE =
            "Can't insert employee";

    private static final String UPDATE_QUERY =
            "UPDATE Employee SET FirstName=?, SecondName=?, LastName=?, " +
            "Age=?, Gender=?, id_Qualification=?, ExperienceInYears=?, " +
            "id_Education=?, Phone_number=?, id_Address=?, SalaryPerDay=?, " +
            "id_Order=? WHERE id_Employee=?";
    private static final String UPDATE_EXCEPTION_MESSAGE =
            "Can't update employee";

    private static final String DELETE_QUERY =
            "DELETE FROM Employee WHERE id_Employee=?";
    private static final String DELETE_EXCEPTION_MESSAGE =
            "Can't delete from Employee with id = ";

    private EmployeeDAO() {
        super();
    }

    public static synchronized EmployeeDAO getInstance() {
        return instance == null ? instance = new EmployeeDAO() : instance;
    }

    @Override
    public Employee findById(int id, Connection connection)
            throws DAOException {
        List<Employee> employees = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_ID_QUERY,
                SELECT_BY_ID_EXCEPTION_MESSAGE + id,
                employees, Employee.class,
                statement -> statement.setInt(1, id)
        );
        if (employees.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE + id);
        }
        return employees.get(0);
    }

    @Override
    public List<Employee> readAll(Connection connection) throws DAOException {
        List<Employee> employees = new LinkedList<>();
        executeReadQuery(connection, SELECT_ALL_QUERY,
                SELECT_ALL_EXCEPTION_MESSAGE,
                employees, Employee.class,
                statement -> {}
        );
        return employees;
    }

    @Override
    public void insert(Employee val, Connection connection)
            throws DAOException {
        executeUpdateQuery(connection, INSERT_QUERY,
                INSERT_EXCEPTION_MESSAGE,
                statement -> {
                    statement.setString(1, val.getFirstName());
                    statement.setString(2, val.getSecondName());
                    statement.setString(3, val.getLastName());
                    statement.setInt(4, val.getAge());
                    statement.setString(5, val.getGender().toSQLString());
                    statement.setInt(6, val.getQualificationId());
                    statement.setInt(7, val.getExperienceYears());
                    statement.setInt(8, val.getEducationId());
                    statement.setString(9, val.getPhoneNumber());
                    statement.setInt(10, val.getAddressId());
                    statement.setInt(11, val.getSalaryPerDay());
                    statement.setInt(12, val.getOrderId());
        });
    }

    @Override
    public void update(Employee val, Connection connection)
            throws DAOException {
        executeUpdateQuery(connection, UPDATE_QUERY,
                UPDATE_EXCEPTION_MESSAGE,
                statement -> {
                    statement.setString(1, val.getFirstName());
                    statement.setString(2, val.getSecondName());
                    statement.setString(3, val.getLastName());
                    statement.setInt(4, val.getAge());
                    statement.setString(5, val.getGender().toSQLString());
                    statement.setInt(6, val.getQualificationId());
                    statement.setInt(7, val.getExperienceYears());
                    statement.setInt(8, val.getEducationId());
                    statement.setString(9, val.getPhoneNumber());
                    statement.setInt(10, val.getAddressId());
                    statement.setInt(11, val.getSalaryPerDay());
                    statement.setInt(12, val.getOrderId());
                    statement.setInt(13, val.getId());
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
