package team.ixigo.hack.com.team1;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import team.ixigo.hack.com.team1.utility.CheckConnection;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent
{
    void inject(MainApp app);

    MainApp getRMSApp();
    RestDataSource getRestDataSource();
    CheckConnection getCheckConnection();
    SharedPreferences getDefaultSharedPreferences();
}
