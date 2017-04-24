package com.netcracker.veromeev.archinc.transaction;

/**
 * Created by jack on 19/04/17.
 *
 * @author Jack Veromeyev
 */
public class TransactionException extends Exception {

    public TransactionException(String s) {
        super(s);
    }

    public TransactionException(String s, Throwable throwable) {
        super(s, throwable);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransactionException{");
        sb.append("\"").append(getMessage()).append("\" caused by ");
        if (getCause() != null) {
            sb.append(getCause());
        } else {
            sb.append("N/A");
        }
        sb.append('}');
        return sb.toString();
    }
}
