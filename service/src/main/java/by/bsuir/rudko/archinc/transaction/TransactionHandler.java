package by.bsuir.rudko.archinc.transaction;

/**
 * Created by jack on 20/04/17.
 *
 * @author Jack Veromeyev
 */
public interface TransactionHandler {
    void runAtomicTransaction(String exceptionMessage,
                              Transaction transaction)
            throws TransactionException;
    void runReadingTransaction(String exceptionMessage,
                               Transaction transaction)
            throws TransactionException;
}
