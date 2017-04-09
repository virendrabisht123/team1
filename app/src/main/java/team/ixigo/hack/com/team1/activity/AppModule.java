package restaurants.search.com.searchrestaurants;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;
import restaurants.search.com.searchrestaurants.database.SqliteDataSource;
import restaurants.search.com.searchrestaurants.utility.CheckConnection;

/**
 * Created by zopper on 24/8/16.
 */
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
    public SqliteDataSource provideSqliteDataSource(){
        return new SqliteDataSource();
    }

    @Provides
    public SharedPreferences provideSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(app.getApplicationContext());
    }
}
