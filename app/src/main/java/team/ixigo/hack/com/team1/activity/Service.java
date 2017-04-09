package restaurants.search.com.searchrestaurants;

import restaurants.search.com.searchrestaurants.model.SearchResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by zopper on 24/8/16.
 */
public interface Service
{
    @GET("/venues/search/")
    void searchRestaurant(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("v") String version,
            @Query("ll") String latLong,
            @Query("limit") String limit,
            @Query("categoryId") String categoryId,
            @Query("query") String query,
            Callback<SearchResponse> callback);
}
