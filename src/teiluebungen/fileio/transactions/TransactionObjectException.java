package teiluebungen.fileio.transactions;

public class TransactionObjectException extends Exception{

    public TransactionObjectException() {
    }

    public TransactionObjectException(String message) {
        super(message);
    }

    public TransactionObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionObjectException(Throwable cause) {
        super(cause);
    }

    public TransactionObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
