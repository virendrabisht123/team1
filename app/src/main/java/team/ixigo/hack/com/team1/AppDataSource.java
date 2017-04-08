package team.ixigo.hack.com.team1;

import retrofit.Callback;

/**
 * Created by virendrapalsingh on 8/4/17.
 */

public interface AppDataSource
{
    public void getSearchCitiesList(String searchFor, String neCategories, String query, Service service, Callback callback);
    public void getSearchCitiesDetilsList(String cityId, String apiKey, Service service, Callback callback);
    public void getRecommendedList(String product, String apiKey, Service service, Callback callback);
    public void getRecommendedListDetails(String cityId, String apiKey, String type, String skip, String limit, Service service, Callback callback);
}
