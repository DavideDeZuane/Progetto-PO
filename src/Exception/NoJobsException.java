package Exception;

//this exception is useful to implement the multithreading
public class NoJobsException extends Exception{
    public NoJobsException(){}
    public NoJobsException(String msg)
    {
        super(msg);
    }
}
