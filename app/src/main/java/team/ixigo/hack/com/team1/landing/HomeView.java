package team.ixigo.hack.com.team1.landing;

import retrofit.RetrofitError;
import retrofit.client.Response;
import team.ixigo.hack.com.team1.model.response.RecommendedListResponse;

public interface HomeView
{
    void getRecommendedListSuccess(RecommendedListResponse response, Response retrofitResponse);

    void getRecommendedListError(RetrofitError error);

    void showProgressBar();

    void hideProgressBar();

    void showToastMessage();
}
