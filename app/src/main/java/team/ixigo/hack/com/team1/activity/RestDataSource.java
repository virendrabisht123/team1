package restaurants.search.com.searchrestaurants;

import restaurants.search.com.searchrestaurants.datasource.SearchDataSource;
import retrofit.Callback;

/**
 * Created by zopper on 24/8/16.
 */
public class RestDataSource implements SearchDataSource
{
    @Override
    public void getSearchRestaurantList(String clientId, String clientSecret, String version, String latLong, String limit, String categoryId, String query, Service service, Callback callback)
    {
        service.searchRestaurant(clientId, clientSecret, version, latLong, limit, categoryId, query, callback);
    }
}
