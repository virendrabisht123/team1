package team.ixigo.hack.com.team1.search;

import retrofit.Callback;
import team.ixigo.hack.com.team1.Service;
import team.ixigo.hack.com.team1.utility.CheckConnection;

public interface SearchPresenter
{
    void getSearchList(String searchFor, String neCategories, String query, CheckConnection checkConnection, Service service);
}
