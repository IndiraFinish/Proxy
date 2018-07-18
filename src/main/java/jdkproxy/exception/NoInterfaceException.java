package jdkproxy.exception;

public class NoInterfaceException extends RuntimeException {
    public NoInterfaceException() {
        super();
    }

    public NoInterfaceException(String message) {
        super(message);
    }

    public NoInterfaceException(String message, Throwable cause) {
        super(message, cause);
    }
}
