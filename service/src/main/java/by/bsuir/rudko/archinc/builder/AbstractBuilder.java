package by.bsuir.rudko.archinc.builder;

import by.bsuir.rudko.archinc.transaction.AbstractTransactionHandler;
import by.bsuir.rudko.archinc.transaction.TransactionException;
import by.bsuir.rudko.archinc.transaction.Transaction;

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
