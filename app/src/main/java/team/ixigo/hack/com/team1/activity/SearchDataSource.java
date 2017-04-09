package team.ixigo.hack.com.team1;

import retrofit.Callback;
import team.ixigo.hack.com.team1.activity.Service;

/**
 * Created by virendrapalsingh on 8/4/17.
 */

public interface SearchDataSource
{
    public void getSearchRestaurantList(String clientId, String clientSecret, String version, String latLong, String limit, String categoryId, String query, Service service, Callback callback);
}
