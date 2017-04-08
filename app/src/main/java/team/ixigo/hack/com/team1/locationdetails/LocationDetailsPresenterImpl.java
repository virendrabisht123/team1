package team.ixigo.hack.com.team1.locationdetails;

import retrofit.RetrofitError;
import retrofit.client.Response;
import team.ixigo.hack.com.team1.CustomCallback;
import team.ixigo.hack.com.team1.ResponseCallback;
import team.ixigo.hack.com.team1.RestDataSource;
import team.ixigo.hack.com.team1.Service;
import team.ixigo.hack.com.team1.model.response.SearchLocationDetailsResponse;
import team.ixigo.hack.com.team1.utility.CheckConnection;

public class LocationDetailsPresenterImpl implements ResponseCallback, LocationDetailsPresenter
{
    LocationDetailsView searchView;
    RestDataSource restDataSource;

    public LocationDetailsPresenterImpl(LocationDetailsView searchView, RestDataSource restDataSource)
    {
        this.searchView = searchView;
        this.restDataSource = restDataSource;
    }

    @Override
    public void onSuccessResult(Object object, Response response)
    {
        searchView.hideProgressBar();
        searchView.getSearchDetailsSuccess((SearchLocationDetailsResponse) object, response);
    }

    @Override
    public void onFailureResult(RetrofitError error)
    {
        searchView.hideProgressBar();
        searchView.getSearchDetailsError(error);
    }

    @Override
    public void getLocationDetails(String cityId, String apiKey, CheckConnection checkConnection, Service service)
    {
        if(checkConnection.isConnectingToInternet())
        {
            searchView.showProgressBar();
            CustomCallback customCallback = new CustomCallback(this);
            restDataSource.getSearchCitiesDetilsList(cityId, apiKey, service, customCallback);
        }
        else
        {
            searchView.hideProgressBar();
            searchView.showToastMessage();
        }
    }
}
