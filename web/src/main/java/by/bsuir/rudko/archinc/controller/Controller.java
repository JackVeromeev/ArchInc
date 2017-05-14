package by.bsuir.rudko.archinc.controller;

import by.bsuir.rudko.archinc.assistant.cookie.CookieException;
import by.bsuir.rudko.archinc.assistant.cookie.CookieHandler;
import by.bsuir.rudko.archinc.command.Command;
import by.bsuir.rudko.archinc.command.factory.CommandFactory;
import by.bsuir.rudko.archinc.entity.User;
import by.bsuir.rudko.archinc.enumeration.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jack on 24/04/17.
 *
 * @author Jack Veromeyev
 */
public class Controller extends HttpServlet {

    private static Logger LOG = LoggerFactory.getLogger(Controller.class);

    private boolean test = false;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("open post");
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("open get");
        if (test) {
            request.getRequestDispatcher("test.jsp")
                    .forward(request, response);
        } else  {
            processRequest(request, response);
        }
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        Command command = CommandFactory.getInstance().parseCommand(request);

        User user;
        try {
            user = CookieHandler.getInstance().parseUserFromCookie(request);
        } catch (CookieException e) {
            LOG.info("wrong user in cookies: " + e.toString());
            user = new User(0, UserType.NA, "", "");
        }

        LOG.info("command is " + command.getClass());

        String jspName = command.execute(request, response, user);
        command.prepareInitialPage(jspName, request);

        if (user.getUserType() != UserType.NA) {
            CookieHandler.getInstance().addUserToCookie(user, response);
            LOG.info(user.toString() + " added to cookie");
        }

        request.getRequestDispatcher(jspName).forward(request, response);
    }
}
