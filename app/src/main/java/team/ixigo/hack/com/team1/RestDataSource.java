package team.ixigo.hack.com.team1;

import retrofit.Callback;

public class RestDataSource implements AppDataSource
{
    @Override
    public void getSearchCitiesList(String searchFor, String neCategories, String query, Service service, Callback callback)
    {
        service.getSearchLocation(searchFor, neCategories, query, callback);
    }

    @Override
    public void getSearchCitiesDetilsList(String cityId, String apiKey, Service service, Callback callback)
    {
        service.getCityDetails(cityId, apiKey, callback);
    }

    @Override
    public void getRecommendedList(String product, String apiKey, Service service, Callback callback)
    {
        service.getRecommended(product, apiKey, callback);
    }

    @Override
    public void getRecommendedListDetails(String cityId, String apiKey, String type, String skip, String limit, Service service, Callback callback)
    {
        service.recommendationDetails(cityId, apiKey, type, skip, limit, callback);
    }
}
