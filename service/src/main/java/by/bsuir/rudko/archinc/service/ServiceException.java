package by.bsuir.rudko.archinc.service;

import by.bsuir.rudko.archinc.transaction.TransactionException;

/**
 * Created by jack on 23/04/17.
 *
 * @author Jack Veromeyev
 */
public class ServiceException extends TransactionException {

    ServiceException(String message, Throwable exception) {
        super(message, exception);
    }

    ServiceException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return  "Service exception: "
                + super.getMessage() + '\n' + super.getCause();
    }
}
