package team.ixigo.hack.com.team1.exception;

public class IxigoException extends Exception
{
    private static final long serialVersionUID = 1L;

    private int exceptionCode;
    private String exceptionMessage;

    public IxigoException()
    {
    }

    //Constructor
    public IxigoException(int exceptionCode)
    {
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = "";
    }

    //Constructor
    public IxigoException(int exceptionCode, String exceptionMessage)
    {
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    public int getExceptionCode()
    {
        return exceptionCode;
    }

    public String getExceptionMessage()
    {
        return exceptionMessage;
    }
}

