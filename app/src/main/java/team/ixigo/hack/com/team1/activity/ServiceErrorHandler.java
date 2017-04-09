package restaurants.search.com.searchrestaurants;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

/**
 * Created by Virendra Pal Singh on 24/8/16.
 */
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
