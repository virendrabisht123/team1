package restaurants.search.com.searchrestaurants;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by zopper on 24/8/16.
 */
public interface ResponseCallback
{
    void onSuccessResult(Object object, Response response);
    void onFailureResult(RetrofitError error);
}
