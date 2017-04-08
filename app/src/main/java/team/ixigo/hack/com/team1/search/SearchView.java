package team.ixigo.hack.com.team1.search;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import team.ixigo.hack.com.team1.model.response.RecommendedListResponse;
import team.ixigo.hack.com.team1.model.response.SearchResponse;

public interface SearchView
{
    void getSearchListSuccess(List<SearchResponse> response, Response retrofitResponse);

    void getSearchListError(RetrofitError error);

    void showProgressBar();

    void hideProgressBar();

    void showToastMessage();
}
