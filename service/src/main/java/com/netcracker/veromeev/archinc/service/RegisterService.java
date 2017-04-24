package com.netcracker.veromeev.archinc.service;

import com.netcracker.veromeev.archinc.dao.UserDAO;
import com.netcracker.veromeev.archinc.entity.User;
import com.netcracker.veromeev.archinc.enumeration.UserType;
import com.netcracker.veromeev.archinc.util.encryption.SHA512Encryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jack on 23/04/17.
 *
 * @author Jack Veromeyev
 */
public class RegisterService extends AbstractService {

    private static final Logger logger =
            LoggerFactory.getLogger(RegisterService.class);

    private static RegisterService instance = null;

    private RegisterService() {}

    public static synchronized RegisterService getInstance() {
        if (instance == null) {
            instance = new RegisterService();
        }
        return instance;
    }

    /**
     * Checks for existing of such User in database and returns User object of
     * that user
     * @param login user name
     * @param password user password
     * @return User object of this user
     * @throws ServiceException if such user is not found
     * (At ServiceException -> DAOException -> SQLException level) that is
     * thrown from UserDAO.findByNamePass().. or another internal error
     */
    public User signIn(String login, String password) throws ServiceException {
        List<User> users = new LinkedList<>();
        String encryptedPass = SHA512Encryption.encrypt(password);
        runReadingTransaction("signIn(" + login + ", " + password + ")",
                connection -> {
                    User expectedUser = UserDAO.getInstance().
                            findByNamePass(login, encryptedPass, connection);
                    users.add(expectedUser);
        });
        return users.get(0);
    }

    /**
     * Creates new user in database and returns User object of this user
     * @param login user name
     * @param password user password
     * @param userType type of user
     * @return User object with its ID
     * @throws ServiceException if user with such name exists (At
     * ServiceException -> DAOException -> SQLException level) thrown
     * from UserDAO.insert() ... or another internal error
     */
    public User signUp(String login, String password, UserType userType)
            throws ServiceException {
        String encryptedPass = SHA512Encryption.encrypt(password);
        User newUser = new User(0, userType, login, encryptedPass);
        runAtomicTransaction("at signUp(" + login + ", " + password + ")",
                connection -> UserDAO.getInstance().insert(newUser, connection)
        );
        return signIn(newUser.getLogin(), newUser.getPassword());
    }

}