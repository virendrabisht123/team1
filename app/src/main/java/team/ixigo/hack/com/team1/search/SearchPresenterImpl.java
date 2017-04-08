package team.ixigo.hack.com.team1.search;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import team.ixigo.hack.com.team1.CustomCallback;
import team.ixigo.hack.com.team1.ResponseCallback;
import team.ixigo.hack.com.team1.RestDataSource;
import team.ixigo.hack.com.team1.Service;
import team.ixigo.hack.com.team1.model.response.SearchResponse;
import team.ixigo.hack.com.team1.utility.CheckConnection;

public class SearchPresenterImpl implements ResponseCallback, SearchPresenter
{
    SearchView searchView;
    RestDataSource restDataSource;

    public SearchPresenterImpl(SearchView searchView, RestDataSource restDataSource)
    {
        this.searchView = searchView;
        this.restDataSource = restDataSource;
    }

    @Override
    public void onSuccessResult(Object object, Response response)
    {
        searchView.hideProgressBar();
        searchView.getSearchListSuccess((List<SearchResponse>) object, response);
    }

    @Override
    public void onFailureResult(RetrofitError error)
    {
        searchView.hideProgressBar();
        searchView.getSearchListError(error);
    }

    @Override
    public void getSearchList(String searchFor, String neCategories, String query, CheckConnection checkConnection, Service service)
    {
        if(checkConnection.isConnectingToInternet())
        {
            searchView.showProgressBar();
            CustomCallback customCallback = new CustomCallback(this);
            restDataSource.getSearchCitiesList(searchFor, neCategories, query, service, customCallback);
        }
        else
        {
            searchView.hideProgressBar();
            searchView.showToastMessage();
        }
    }
}
