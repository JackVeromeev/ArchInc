package com.netcracker.veromeev.archinc.builder;

import com.netcracker.veromeev.archinc.transaction.TransactionException;

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
        return "Builder exception: '" + getMessage() + "' causerd by " +
                getCause();
    }
}
