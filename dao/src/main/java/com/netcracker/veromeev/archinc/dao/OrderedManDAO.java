package com.netcracker.veromeev.archinc.dao;

import com.netcracker.veromeev.archinc.entity.OrderedMan;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 17/04/17.
 *
 * @author Jack Veromeyev
 */
public class OrderedManDAO extends AbstractDAO<OrderedMan> {

    private static OrderedManDAO instance;

    private static final String SELECT_ALL_QUERY =
            "SELECT id_OrderedMan, id_Order, id_Qualification, Amount " +
            " FROM OrderedMan ";
    private static final String SELECT_ALL_EXCEPTION_MESSAGE =
            "Can't read all Ordered men";

    private static final String SELECT_BY_ID_QUERY =
            SELECT_ALL_QUERY + " WHERE id_OrderedMan=?";
    private static final String SELECT_BY_ID_EXCEPTION_MESSAGE =
            "Can't read a customer order by id = ";

    private static final String INSERT_QUERY =
            "INSERT INTO OrderedMan " +
            "SET id_Order=?, id_Qualification=?, Amount=?";
    private static final String INSERT_EXCEPTION_MESSAGE =
            "Can't insert ordered man";

    private static final String UPDATE_QUERY =
            "UPDATE CustomerOrder " +
            "SET id_Order=?, id_Qualification Amount=? " +
            "WHERE id_OrderedMan=?";
    private static final String UPDATE_EXCEPTION_MESSAGE =
            "Can't update ordered man with id = ";

    private static final String DELETE_QUERY =
            "DELETE FROM OrderedMan WHERE id_OrderedMan=?";
    private static final String DELETE_EXCEPTION_MESSAGE =
            "Can't delete from OrderedMan with id = ";

    private OrderedManDAO() {
        super();
    }

    public static synchronized OrderedManDAO getInstance() {
        if (instance == null) {
            instance = new OrderedManDAO();
        }
        return instance;
    }


    @Override
    public OrderedMan findById(int id, Connection connection)
            throws DAOException {
        List<OrderedMan> orderedManList = new LinkedList<>();
        executeReadQuery(connection, SELECT_BY_ID_QUERY,
                SELECT_BY_ID_EXCEPTION_MESSAGE,
                orderedManList, OrderedMan.class,
                statement -> statement.setInt(1, id));
        if  (orderedManList.size() == 0) {
            throw new DAOException(SELECT_BY_ID_EXCEPTION_MESSAGE);
        }
        return orderedManList.get(0);
    }

    @Override
    public List<OrderedMan> readAll(Connection connection)
            throws DAOException {
        List<OrderedMan> orderedManList = new LinkedList<>();
        executeReadQuery(connection, SELECT_ALL_QUERY,
                SELECT_ALL_EXCEPTION_MESSAGE,
                orderedManList, OrderedMan.class,
                statement -> {}
        );
        return orderedManList;
    }

    @Override
    public void insert(OrderedMan val, Connection connection)
            throws DAOException {
        executeUpdateQuery(connection, INSERT_QUERY,
                INSERT_EXCEPTION_MESSAGE,
                statement -> {
                    statement.setInt(1, val.getOrderId());
                    statement.setInt(2, val.getQualificationId());
                    statement.setInt(3, val.getAmount());
        });
    }

    @Override
    public void update(OrderedMan val, Connection connection)
            throws DAOException {
        executeUpdateQuery(connection, UPDATE_QUERY,
                UPDATE_EXCEPTION_MESSAGE + val.getId(),
                statement -> {
                    statement.setInt(1, val.getOrderId());
                    statement.setInt(2, val.getQualificationId());
                    statement.setInt(3, val.getAmount());
                    statement.setInt(4, val.getId());
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
