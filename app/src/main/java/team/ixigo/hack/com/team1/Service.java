package team.ixigo.hack.com.team1;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import team.ixigo.hack.com.team1.model.response.RecommendedListResponse;
import team.ixigo.hack.com.team1.model.response.SearchLocationDetailsResponse;
import team.ixigo.hack.com.team1.model.response.SearchResponse;
import team.ixigo.hack.com.team1.model.response.SearchResponse1;

public interface Service
{
    @GET("/action/content/zeus/autocomplete/") //1
    void getSearchLocation(
            @Query("searchFor") String searchFor,
            @Query("neCategories") String neCategories,
            @Query("query") String query,
            Callback<List<SearchResponse>> callback);

    @GET("/api/v3/namedentities/id/{cityId}/") //3
    void getCityDetails(
            @Path("cityId") String cityId,
            @Query("apiKey") String apiKey,
            Callback<SearchLocationDetailsResponse> callback);

    @GET("/api/v2/widgets/brand/inspire/") // 4
    void getRecommended(
            @Query("product") String searchFor,
            @Query("apiKey") String neCategories,
            Callback<RecommendedListResponse> callback);

    @GET("/api/v3/namedentities/city/{cityId}/categories/") // 2
    void recommendationDetails(
            @Path("cityId") String cityId,
            @Query("apiKey") String apiKey,
            @Query("type") String type,
            @Query("skip") String skip,
            @Query("limit") String limit,
            Callback<Object> callback
    );


    /*@GET("/action/content/zeus/autocomplete")
    void getRecommendedDetails(
            @Query("searchFor") String searchFor,
            @Query("neCategories=") String neCategories,
            @Query("query") String version,
            Callback<Object> callback);*/
}