package restaurants.search.com.searchrestaurants;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by zopper on 24/8/16.
 */
public class CustomCallback implements Callback
{
    ResponseCallback responseCallback;

    public CustomCallback(ResponseCallback responseCallback)
    {
        this.responseCallback = responseCallback;
    }

    @Override
    public void success(Object object, Response response)
    {
        responseCallback.onSuccessResult(object, response);
    }

    @Override
    public void failure(RetrofitError error)
    {
        responseCallback.onFailureResult(error);
    }
}
