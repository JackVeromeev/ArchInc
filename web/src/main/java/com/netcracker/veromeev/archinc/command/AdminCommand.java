package com.netcracker.veromeev.archinc.command;

import com.netcracker.veromeev.archinc.entity.User;
import com.netcracker.veromeev.archinc.enumeration.UserType;
import com.netcracker.veromeev.archinc.jspname.JSPName;
import com.netcracker.veromeev.archinc.service.AdminService;
import com.netcracker.veromeev.archinc.service.RegisterService;
import com.netcracker.veromeev.archinc.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminCommand extends Command {

    private static Logger LOG = LoggerFactory.getLogger(AdminCommand.class);

    private static AdminCommand instance = null;

    private AdminCommand() {}

    public static synchronized AdminCommand getInstance() {
        return (instance == null) ? instance = new AdminCommand() : instance;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response,
                          User user) {
        String action = request.getParameter("action");
        String returnPage = JSPName.LOGIN;
        if ("createuser".equals(action)) {
            returnPage = JSPName.ADD_USER;
        } else if ("edituser".equals(action)) {
            returnPage = editUser(request);
        } else if ("deleteuser".equals(action)) {
            returnPage = deleteUser(request, user);
        } else if ("submitcreate".equals(action)) {
            returnPage = submitCreateUser(request);
        } else if ("submitedit".equals(action)) {
            returnPage = submitEditUser(request);
        } else if ("cancel".equals(action)) {
            returnPage  = cancel();
        }
        return returnPage;
    }

    private String submitEditUser(HttpServletRequest request) {

        String parsedId = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(parsedId);
        } catch (NumberFormatException e) {
            id = 0;
        }
        String login = request.getParameter("login");
        String newPassword = request.getParameter("password");
        String parsedUserType = request.getParameter("usertype");
        UserType userType;
        try {
            userType = UserType.valueOf(parsedUserType);
        } catch (IllegalArgumentException e) {
            userType = null;
        }
        User oldUser = null;
        try {
            oldUser = AdminService.getInstance().getUserById(id);
        } catch (ServiceException e) {}
        if (login == null || id == 0 || oldUser == null) {
            String message = "Illegal params: id=" + id + ", login=" + login;
            LOG.error(message);
            request.setAttribute("message", message);
        } else if (RegisterService.getInstance().usernameExists(login)
                && !oldUser.getLogin().equals(login)) {
            String message = "Such login (" + login + ") already exists";
            LOG.info(message);
            request.setAttribute("message", message);
        } else {
            try {
                String password = (newPassword == null)
                        ? oldUser.getPassword()
                        : newPassword;
                User user = new User(id, userType, login, password);
                RegisterService.getInstance().updateUser(user);
            } catch (ServiceException e) {
                LOG.error("Internal error (see trace)", e);
                request.setAttribute("message", "Internal error occured");
            }
        }
        return JSPName.ADMIN;
    }

    private String submitCreateUser(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserType userType;
        String parsedUserType = request.getParameter("usertype");
        try {
            userType = UserType.valueOf(parsedUserType);
        } catch (IllegalArgumentException e) {
            userType = null;
        }
        if (login == null || password == null || userType == null) {
            LOG.error("Wrong arguments for creation: login='" + login +
                    "', pass='" + password + "', parsed usertype='" +
                    parsedUserType + '\'');
            request.setAttribute("message",
                    "User creation form was corrupted");
        } else {
            if (RegisterService.getInstance().usernameExists(login)) {
                request.setAttribute("message",
                        "Such username already exists");
            } else {
                try {
                    RegisterService.getInstance().signUp(
                            login, password, userType);
                    request.setAttribute("message",
                            "User created successfully");
                } catch (ServiceException e) {
                    LOG.error("Error at creation user in db", e);
                    request.setAttribute("message",
                            "User creation filed because of internal error");
                }
            }
        }
        return JSPName.ADMIN;
    }

    private String cancel() {
        return JSPName.ADMIN;
    }

    private String deleteUser(HttpServletRequest request, User currentUser) {
        int id = Integer.parseInt(request.getParameter("id"));
        if (currentUser.getId() == id) {
            request.setAttribute("message", "You may not delete yourself");
        } else{
            try {
                AdminService.getInstance().deleteUser(id);
                request.setAttribute("message", "User successfully deleted");

            } catch (ServiceException e) {
                LOG.error("can't delete user object",e);
                request.setAttribute("message", "User wasn't deleted");
            }
        }
        return JSPName.ADMIN;
    }



    private String editUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            User user = AdminService.getInstance().getUserById(id);
            request.setAttribute("user", user);
            return JSPName.EDIT_USER;
        } catch (ServiceException e) {
            LOG.error("can't get user object");
            request.setAttribute("message", "User wasn't created");
            return JSPName.ADMIN;
        }
    }
}
