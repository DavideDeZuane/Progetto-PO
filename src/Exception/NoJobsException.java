package Exception;

//this exception is not used anymore
public class NoJobsException extends Exception{
    public NoJobsException(){}
    public NoJobsException(String msg)
    {
        super(msg);
    }
}
