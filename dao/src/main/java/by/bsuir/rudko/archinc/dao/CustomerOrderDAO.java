package by.bsuir.rudko.archinc.dao;

import by.bsuir.rudko.archinc.entity.CustomerOrder;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 15/04/17.
 *
 * @author Jack Veromeyev
 */
public class CustomerOrderDAO extends AbstractDAO<CustomerOrder> {

    private static CustomerOrderDAO instance = null;

    private static final String SELECT_ALL_QUERY =
            "SELECT id_Order, id_User, Title, Description, OrderState " +
            " FROM CustomerOrder";
    private static final String SELECT_ALL_EXCEPTION_MESSAGE =
            "Can't read all customer orders";

    private static final String SELECT_BY_ID_QUERY =
            SELECT_ALL_QUERY + " WHERE id_Order = ?";
    private static final String SELECT_BY_ID_EXCEPTION_MESSAGE =
            "Can't read a customer order by id = ";

    private static final String INSERT_QUERY =
            "INSERT INTO CustomerOrder " +
            "SET Title=?, Description=?, id_User=?, OrderState=?";
    private static final String INSERT_EXCEPTION_MESSAGE =
            "Can't insert customer order with title = ";

    private static final String UPDATE_QUERY =
            "UPDATE CustomerOrder " +
            "SET Title=?, Description=?, id_User=?, OrderState=? " +
            "WHERE id_Order=?";
    private static final String UPDATE_EXCEPTION_MESSAGE =
            "Can't update customer order with title = ";

    private static final String DELETE_QUERY =
            "DELETE FROM CustomerOrder WHERE id_Order=?";
    private static final String DELETE_EXCEPTION_MESSAGE =
            "Can't delete from CustomerOrder with id = ";

    private CustomerOrderDAO() {
        super();

    }

    public static synchronized CustomerOrderDAO getInstance() {
        if (instance == null) {
            instance = new CustomerOrderDAO();
        }
        return instance;
    }

    @Override
    public CustomerOrder findById(int id, Connection connection) throws DAOException {
        List<CustomerOrder> customerOrders = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_ID_QUERY,
                SELECT_BY_ID_EXCEPTION_MESSAGE + id,
                customerOrders, CustomerOrder.class,
                statement -> statement.setInt(1, id));
        if (customerOrders.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE + id);
        } else {
            return customerOrders.get(0);
        }
    }

    @Override
    public List<CustomerOrder> readAll(Connection connection) throws DAOException {
        List<CustomerOrder> customerOrders = new LinkedList<>();
        executeReadQuery(connection, SELECT_ALL_QUERY,
                SELECT_ALL_EXCEPTION_MESSAGE,
                customerOrders, CustomerOrder.class,
                statement -> {}
        );
        return customerOrders;
    }

    @Override
    public void insert(CustomerOrder val, Connection connection) throws DAOException {
        executeUpdateQuery(connection, INSERT_QUERY,
                INSERT_EXCEPTION_MESSAGE + val.getTitle(),
                statement -> {
                    statement.setString(1, val.getTitle());
                    statement.setString(2, val.getDescription());
                    statement.setInt(3, val.getUserId());
                    statement.setString(4, val.getOrderState().toSQLString());

        });
    }

    @Override
    public void update(CustomerOrder val, Connection connection) throws DAOException {
        executeUpdateQuery(connection, UPDATE_QUERY,
                UPDATE_EXCEPTION_MESSAGE + val.getTitle(),
                statement -> {
                    statement.setString(1, val.getTitle());
                    statement.setString(2, val.getDescription());
                    statement.setInt(3, val.getUserId());
                    statement.setString(4, val.getOrderState().toSQLString());
                    statement.setInt(5, val.getId());
        });
    }

    @Override
    public void deleteById(int id, Connection connection) throws DAOException {
        executeUpdateQuery(connection, DELETE_QUERY,
                DELETE_EXCEPTION_MESSAGE + id,
                statement -> statement.setInt(1, id));
    }

}
