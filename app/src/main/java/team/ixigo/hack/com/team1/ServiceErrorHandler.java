package team.ixigo.hack.com.team1;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class ServiceErrorHandler implements ErrorHandler
{
    @Override
    public Throwable handleError(RetrofitError cause)
    {
        String errorDescription; //TODO : Change this generic message

        if(cause.getKind() == RetrofitError.Kind.NETWORK)
        {
            errorDescription = "Network Not Available !";
        }
        else
        {
            return cause;
        }

        return new Exception(errorDescription);
    }
}
