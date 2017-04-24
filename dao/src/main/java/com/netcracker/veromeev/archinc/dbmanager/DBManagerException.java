package com.netcracker.veromeev.archinc.dbmanager;

/**
 * Created by jack on 12/04/17.
 *
 * @author Jack Veromeyev
 */
public class DBManagerException extends Exception {

    DBManagerException(String message, Throwable exception) {
        super(message, exception);
    }

    DBManagerException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DBManagerException: "
                + super.getMessage() + '\n' + super.getCause();
    }
}
