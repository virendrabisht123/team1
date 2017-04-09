package restaurants.search.com.searchrestaurants;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import restaurants.search.com.searchrestaurants.database.SqliteDataSource;
import restaurants.search.com.searchrestaurants.utility.CheckConnection;

/**
 * Created by zopper on 24/8/16.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent
{
    void inject(MainApp app);

    MainApp getRMSApp();
    RestDataSource getRestDataSource();
    SqliteDataSource getSqliteDataSource();
    CheckConnection getCheckConnection();
    SharedPreferences getDefaultSharedPreferences();
}
