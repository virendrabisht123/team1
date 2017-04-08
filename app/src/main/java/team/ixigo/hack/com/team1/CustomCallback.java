package team.ixigo.hack.com.team1;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
