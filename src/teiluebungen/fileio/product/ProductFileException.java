package teiluebungen.fileio.product;

public class ProductFileException extends Exception{

    public ProductFileException() {
    }

    public ProductFileException(String message) {
        super(message);
    }

    public ProductFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductFileException(Throwable cause) {
        super(cause);
    }

    public ProductFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
