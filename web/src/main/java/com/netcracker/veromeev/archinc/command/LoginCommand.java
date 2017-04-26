package com.netcracker.veromeev.archinc.command;

import com.netcracker.veromeev.archinc.assistant.cookie.CookieHandler;
import com.netcracker.veromeev.archinc.entity.User;
import com.netcracker.veromeev.archinc.enumeration.UserType;
import com.netcracker.veromeev.archinc.jspname.JSPName;
import com.netcracker.veromeev.archinc.service.RegisterService;
import com.netcracker.veromeev.archinc.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jack on 24/04/17.
 *
 * @author Jack Veromeyev
 */
public class LoginCommand extends Command {

    private static Logger LOG = LoggerFactory.getLogger(LoginCommand.class);

    private static LoginCommand instance = null;

    private LoginCommand() {}

    public static synchronized LoginCommand getInstance() {
        return (instance == null) ? instance = new LoginCommand() : instance;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response, User user) {
        String returnPage = JSPName.LOGIN;

        try {
            String type = request.getParameter("type");
            LOG.info("type="+type);

            if ("logout".equals(type)) {
                return processLogOut(user, response);
            }

            String login = request.getParameter("login");
            String password = request.getParameter("password");

            LOG.info("login="+login+" pass="+password);

            if (login == null || password == null) {
                throw new CommandException(
                        "Login or password doesn't exist");
            }
            if ("signin".equals(type)) {
                returnPage = processSignIn(login, password, user);
            } else if ("signup".equals(type)) {
                returnPage = processSignUp(login, password, user);
            } else {
                throw new CommandException("Wrong button pressed");
            }

        } catch (CommandException e) {
            LOG.info("at execute()", e);
            request.setAttribute("message", e.getMessage());
        }

        LOG.info("return page = " + returnPage);
        return returnPage;
    }

    private String processSignIn (String login,String password, User user)
            throws CommandException {
        LOG.info("sign in");
        try {
            User registeredUser =
                    RegisterService.getInstance().signIn(login, password);
            user.setUser(registeredUser);
        } catch (ServiceException e) {
            LOG.info("catched exception at sign in");
            throw new CommandException("Wrong login or password", e);
        }
        return getPageForUser(user);
    }

    private String processSignUp(String login,String password, User user)
            throws CommandException {
        LOG.info("sign up");
        try {
            User newUser =
                    RegisterService.getInstance().signUp(login, password,
                            UserType.CUSTOMER);
            user.setUser(newUser);
        } catch (ServiceException e) {
            // maybe user is added, but the exception is thrown (idk why)
            LOG.info("catched exception at sign up");
            try {
                processSignIn(login, password, user);
            } catch (CommandException e1) {
//                 if this user was not created, good bue
                throw new CommandException("Such username already exists", e1);
            }

        }
        return getPageForUser(user);
    }

    private String processLogOut(User user, HttpServletResponse response) {
        user.setLogin("");
        user.setPassword("");
        CookieHandler.getInstance().addUserToCookie(user, response);
        return JSPName.LOGIN;
    }
}
