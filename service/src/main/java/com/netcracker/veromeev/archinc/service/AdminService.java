package com.netcracker.veromeev.archinc.service;

import com.netcracker.veromeev.archinc.dao.UserDAO;
import com.netcracker.veromeev.archinc.entity.User;
import com.netcracker.veromeev.archinc.enumeration.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 20/04/17.
 *
 * @author Jack Veromeyev
 */
public class AdminService extends AbstractService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    private static AdminService instance = null;

    private AdminService() {}

    public static synchronized AdminService getInstance() {
        if (instance == null) {
            instance = new AdminService();
        }
        return instance;
    }

    public List<User> getUsers() throws ServiceException {
        List<User> users = new LinkedList<>();
        runReadingTransaction("getUsers()", connection -> {
            List<User> userList = UserDAO.getInstance().readAll(connection);
            userList.forEach(users::add);
        });
        return users;
    }

    public void editUser(User user) throws ServiceException {
        runAtomicTransaction("editUser(" + user.getLogin() + ")",
                connection -> UserDAO.getInstance().update(user, connection)
        );
    }

    public User getUserById(int id) throws ServiceException {
        User user = new User(0, UserType.NA, "", "");
        runReadingTransaction("getUserById(" + id + ")",
                connection -> user.setUser(UserDAO.getInstance().findById(
                        id, connection))
        );
        return user;
    }

    public void deleteUser(int id) throws ServiceException {
        runAtomicTransaction("deleteUser(" + id + ")",
                connection -> UserDAO.getInstance().deleteById(id, connection)
        );
    }
}
