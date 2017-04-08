package team.ixigo.hack.com.team1.landing;

import team.ixigo.hack.com.team1.Service;
import team.ixigo.hack.com.team1.utility.CheckConnection;

public interface HomePresenter
{
    void getRecommendedList(String product, String apiKey, CheckConnection checkConnection, Service service);
}
