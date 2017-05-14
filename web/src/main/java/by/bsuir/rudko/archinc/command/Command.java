package by.bsuir.rudko.archinc.command;

import by.bsuir.rudko.archinc.entity.User;
import by.bsuir.rudko.archinc.builder.EmployeeBuilder;
import by.bsuir.rudko.archinc.jspname.JSPName;
import by.bsuir.rudko.archinc.service.AdminService;
import by.bsuir.rudko.archinc.service.HRServise;
import by.bsuir.rudko.archinc.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jack on 24/04/17.
 *
 * @author Jack Veromeyev
 */
public abstract class Command {

    private static final Logger LOG = LoggerFactory.getLogger(Command.class);

    public abstract String execute(HttpServletRequest request,
                   HttpServletResponse response,
                   User user);

    protected String getPageForUser(User user) {
        switch (user.getUserType()) {
            case ADMIN:
                return JSPName.ADMIN;
            case CUSTOMER:
                return JSPName.CUSTOMER;
            case HUMAN_RESOURCE:
                return JSPName.HR;
            case MANAGER:
                return JSPName.MANAGER;
            default: return JSPName.LOGIN;
        }
    }

    public void prepareInitialPage(String page, HttpServletRequest request) {
        try{
            switch (page) {
                case JSPName.ADMIN:
                    initAdmin(request);
                    break;
                case JSPName.CUSTOMER:
                    break;
                case JSPName.HR:
                    initHR(request);
                    break;
                case JSPName.MANAGER:
                    break;
                default:
                    break;
            }
        } catch (ServiceException e) {
            LOG.error("exception in init page ", e);
        }
    }

    private void initHR(HttpServletRequest request)
            throws ServiceException{
        List<EmployeeBuilder> employees =
                HRServise.getInstanse().getQualificatedEmployees();
        request.setAttribute("employees", employees);
    }

    private void initAdmin(HttpServletRequest request)
            throws ServiceException {
        List<User> users = AdminService.getInstance().getUsers();
        request.setAttribute("users", users);
    }



}
