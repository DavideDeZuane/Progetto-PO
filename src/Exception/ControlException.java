package Exception;

import java.net.URI;

public class ControlException extends Exception {


    private static final long serialVersionUID = 1885653349235601203L;

    /**
     * default constructor
     */
    public ControlException() {
    }

    /**
     * constructor
     * @param message /////////////////////////////////
     */
    public ControlException(URI message) {
        super(String.valueOf(message));
    }
}