package com.netcracker.veromeev.archinc.command;

import com.netcracker.veromeev.archinc.entity.User;
import com.netcracker.veromeev.archinc.jspname.JSPName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jack on 25/04/17.
 *
 * @author Jack Veromeyev
 */
public class EmptyCommand extends Command {

    private static EmptyCommand instance = null;

    private EmptyCommand() {}

    public static synchronized EmptyCommand getInstance() {
        return (instance == null) ? instance = new EmptyCommand() : instance;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response,
                          User user) {
        if (user != null) {
            return getPageForUser(user);
        }
        return JSPName.LOGIN;
    }
}
