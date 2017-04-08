package team.ixigo.hack.com.team1;

import retrofit.RetrofitError;
import retrofit.client.Response;

public interface ResponseCallback
{
    void onSuccessResult(Object object, Response response);
    void onFailureResult(RetrofitError error);
}
