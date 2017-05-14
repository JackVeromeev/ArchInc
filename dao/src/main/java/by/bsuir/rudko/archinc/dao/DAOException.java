package by.bsuir.rudko.archinc.dao;

public class DAOException extends Exception {

    DAOException(String message, Throwable exception) {
        super(message, exception);
    }

    DAOException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return  "DAO Exception: "
                + super.getMessage() + '\n' + super.getCause();
    }

}
