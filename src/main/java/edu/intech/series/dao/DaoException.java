package edu.intech.series.dao;

public class DaoException extends RuntimeException {
    /*
     * Constructor
     */
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
