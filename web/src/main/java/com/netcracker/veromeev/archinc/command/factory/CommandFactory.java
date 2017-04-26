package com.netcracker.veromeev.archinc.command.factory;

import javax.servlet.http.HttpServletRequest;

import com.netcracker.veromeev.archinc.command.Command;
import com.netcracker.veromeev.archinc.command.EmptyCommand;
import com.netcracker.veromeev.archinc.command.LoginCommand;
import com.netcracker.veromeev.archinc.command.name.CommandName;

/**
 * Created by jack on 24/04/17.
 *
 * @author Jack Veromeyev
 */
public class CommandFactory {

    private static CommandFactory instance = null;

    private CommandFactory() {}

    public static synchronized CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public Command parseCommand(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commandName == null) {
            return EmptyCommand.getInstance();
        }
        switch (commandName) {
            case CommandName.LOGIN:
                return LoginCommand.getInstance();
            default:
                return EmptyCommand.getInstance();
        }
    }

}
