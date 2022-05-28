package teiluebungen.fileio.transactions;

public class TransactionLoadException extends Exception{

    public TransactionLoadException() {
    }

    public TransactionLoadException(String message) {
        super(message);
    }

    public TransactionLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionLoadException(Throwable cause) {
        super(cause);
    }

    public TransactionLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
