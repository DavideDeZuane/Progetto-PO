package Exception;

//this exception is useful to implement the multithreading
public class NoJobsException extends Exception{

    //eccezione non utilizzata

    /**
     * constructor
     */
    public NoJobsException(){}

    /**
     * constructor
     * @param msg message
     */
    public NoJobsException(String msg)
    {
        super(msg);
    }
}
