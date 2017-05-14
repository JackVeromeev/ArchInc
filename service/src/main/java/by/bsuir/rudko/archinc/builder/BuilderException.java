package by.bsuir.rudko.archinc.builder;

import by.bsuir.rudko.archinc.transaction.TransactionException;

/**
 * Created by jack on 27/04/17.
 *
 * @author Jack Veromeyev
 */
public class BuilderException extends TransactionException {

    public BuilderException(String s) {
        super(s);
    }

    public BuilderException(String s, Throwable throwable) {
        super(s, throwable);
    }

    @Override
    public String toString() {
        return "Builder exception: '" + getMessage() + "' caused by " +
                getCause();
    }
}
