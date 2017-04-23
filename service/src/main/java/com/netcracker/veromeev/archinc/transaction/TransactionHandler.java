package com.netcracker.veromeev.archinc.transaction;

/**
 * Created by jack on 20/04/17.
 *
 * @author Jack Veromeyev
 */
public interface TransactionHandler {
    void run(Transaction transaction) throws TransactionException;
}
