package com.netcracker.veromeev.archinc.command;

import com.netcracker.veromeev.archinc.entity.User;
import com.netcracker.veromeev.archinc.jspname.JSPName;
import com.netcracker.veromeev.archinc.service.AdminService;
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
            returnPage = deleteUser(request);
        }
        return returnPage;
    }

    private String deleteUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            AdminService.getInstance().deleteUser(id);
            request.setAttribute("message", "User successfully deleted");

        } catch (ServiceException e) {
            LOG.error("can't delete user object",e);
            request.setAttribute("message", "User wasn't deleted");
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
