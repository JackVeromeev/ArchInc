package com.netcracker.veromeev.archinc.builder;

import com.netcracker.veromeev.archinc.transaction.AbstractTransactionHandler;
import com.netcracker.veromeev.archinc.transaction.Transaction;
import com.netcracker.veromeev.archinc.transaction.TransactionException;

/**
 * Builders are classes to work with complex entities (orders, employees)
 *
 * Created by jack on 27/04/17.
 *
 * @author Jack Veromeyev
 */
abstract class AbstractBuilder extends AbstractTransactionHandler {

    @Override
    public void runAtomicTransaction(String methodName,
                                     Transaction transaction)
            throws BuilderException {
        try {
            super.runAtomicTransaction(methodName, transaction);
        } catch (TransactionException e) {
            throw new BuilderException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void runReadingTransaction(String methodName,
                                      Transaction transaction)
            throws BuilderException {
        try {
            super.runAtomicTransaction(methodName, transaction);
        } catch (TransactionException e) {
            throw new BuilderException(e.getMessage(), e.getCause());
        }
    }

    public abstract void synchronizeWithDB() throws BuilderException;

}
