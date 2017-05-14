package by.bsuir.rudko.archinc.service;

import by.bsuir.rudko.archinc.transaction.AbstractTransactionHandler;
import by.bsuir.rudko.archinc.transaction.Transaction;
import by.bsuir.rudko.archinc.transaction.TransactionException;

/**
 * Created by jack on 23/04/17.
 *
 * @author Jack Veromeyev
 */
public abstract class AbstractService extends AbstractTransactionHandler {

    @Override
    public void runAtomicTransaction(String methodName,
                                     Transaction transaction)
            throws ServiceException {
        try {
            super.runAtomicTransaction(methodName, transaction);
        } catch (TransactionException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void runReadingTransaction(String methodName,
                                      Transaction transaction)
            throws ServiceException {
        try {
            super.runReadingTransaction(methodName, transaction);
        } catch (TransactionException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
