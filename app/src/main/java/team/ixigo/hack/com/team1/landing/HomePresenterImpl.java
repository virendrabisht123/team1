package team.ixigo.hack.com.team1.landing;

import retrofit.RetrofitError;
import retrofit.client.Response;
import team.ixigo.hack.com.team1.CustomCallback;
import team.ixigo.hack.com.team1.ResponseCallback;
import team.ixigo.hack.com.team1.RestDataSource;
import team.ixigo.hack.com.team1.Service;
import team.ixigo.hack.com.team1.model.response.RecommendedListResponse;
import team.ixigo.hack.com.team1.utility.CheckConnection;

public class HomePresenterImpl implements ResponseCallback, HomePresenter
{
    HomeView homeView;
    RestDataSource restDataSource;

    public HomePresenterImpl(HomeView searchView, RestDataSource restDataSource)
    {
        this.homeView = searchView;
        this.restDataSource = restDataSource;
    }

    @Override
    public void onSuccessResult(Object object, Response response)
    {
        homeView.hideProgressBar();
        homeView.getRecommendedListSuccess((RecommendedListResponse) object, response);
    }

    @Override
    public void onFailureResult(RetrofitError error)
    {
        homeView.hideProgressBar();
        homeView.getRecommendedListError(error);
    }

    @Override
    public void getRecommendedList(String product, String apiKey, CheckConnection checkConnection, Service service)
    {
        if(checkConnection.isConnectingToInternet())
        {
            homeView.showProgressBar();
            CustomCallback customCallback = new CustomCallback(this);
            restDataSource.getRecommendedList(product, apiKey, service, customCallback);
        }
        else
        {
            homeView.hideProgressBar();
            homeView.showToastMessage();
        }
    }
}
