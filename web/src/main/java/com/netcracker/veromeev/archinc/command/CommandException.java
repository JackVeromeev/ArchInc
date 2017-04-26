package com.netcracker.veromeev.archinc.command;

/**
 * Created by jack on 25/04/17.
 *
 * @author Jack Veromeyev
 */
public class CommandException extends Exception {
    CommandException(String message, Throwable exception) {
        super(message, exception);
    }

    CommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return  "Command exception: "
                + super.getMessage() + '\n' + super.getCause();
    }
}
