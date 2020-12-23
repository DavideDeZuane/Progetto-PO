package Exception;

public class NoJobsException extends Exception{
    public NoJobsException(){}
    public NoJobsException(String msg)
    {
        super(msg);
    }
}
