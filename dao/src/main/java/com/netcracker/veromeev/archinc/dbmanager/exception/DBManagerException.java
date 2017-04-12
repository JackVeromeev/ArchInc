package com.netcracker.veromeev.archinc.dbmanager.exception;

/**
 * Created by jack on 12/04/17.
 *
 * @author Jack Veromeyev
 */
public class DBManagerException extends Exception {

    public DBManagerException(String message, Throwable exception) {
        super(message, exception);
    }

    public DBManagerException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DBManagerException: "
                + super.getMessage() + '\n' + super.getCause();
    }
}
