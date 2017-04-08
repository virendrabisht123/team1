package team.ixigo.hack.com.team1.locationdetails;

import team.ixigo.hack.com.team1.Service;
import team.ixigo.hack.com.team1.utility.CheckConnection;

public interface LocationDetailsPresenter
{
    void getLocationDetails(String cityId, String apiKey, CheckConnection checkConnection, Service service);
}
