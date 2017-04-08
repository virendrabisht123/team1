package team.ixigo.hack.com.team1.locationdetails;

import retrofit.RetrofitError;
import retrofit.client.Response;
import team.ixigo.hack.com.team1.model.response.SearchLocationDetailsResponse;

public interface LocationDetailsView
{
    void getSearchDetailsSuccess(SearchLocationDetailsResponse response, Response retrofitResponse);

    void getSearchDetailsError(RetrofitError error);

    void showProgressBar();

    void hideProgressBar();

    void showToastMessage();
}
