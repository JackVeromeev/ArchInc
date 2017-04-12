package com.netcracker.veromeev.archinc.dao.exception;

public class DAOException extends Exception {

    public DAOException(String message, Throwable exception) {
        super(message, exception);
    }

    public DAOException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return  "DBManagerException: "
                + super.getMessage() + '\n' + super.getCause();
    }

}
