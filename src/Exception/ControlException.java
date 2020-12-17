package Exception;

import java.net.URI;

public class ControlException extends Exception {

    private static final long serialVersionUID = 1885653349235601203L;

    public ControlException() {
    }

    public ControlException(URI message) {
        super(String.valueOf(message));
    }
/*
    public ControlException(Throwable cause) {
        super(cause);
    }

    public ControlException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }*/
}
