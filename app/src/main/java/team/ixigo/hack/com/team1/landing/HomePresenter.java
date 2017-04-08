package team.ixigo.hack.com.team1.landing;

import team.ixigo.hack.com.team1.Service;

public interface HomePresenter
{
    void getRecommendedList(String product, String apiKey, Service service);
}
