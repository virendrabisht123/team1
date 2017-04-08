package team.ixigo.hack.com.team1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;
import team.ixigo.hack.com.team1.utility.CheckConnection;

@Module
public class AppModule
{
    private final MainApp app;

    public AppModule(MainApp app)
    {
        this.app = app;
    }

    @Provides
    public MainApp provideApplication(){
        return app;
    }

    @Provides
    public CheckConnection provideCheckConnection(MainApp app){
        return new CheckConnection(app.getApplicationContext());
    }

    @Provides
    public RestDataSource provideRestDataSource(){
        return new RestDataSource();
    }

    @Provides
    public SharedPreferences provideSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());
    }
}
