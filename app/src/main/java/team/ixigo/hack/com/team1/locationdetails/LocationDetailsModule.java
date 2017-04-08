package team.ixigo.hack.com.team1.locationdetails;

import dagger.Module;
import dagger.Provides;
import team.ixigo.hack.com.team1.RestDataSource;

@Module
public class LocationDetailsModule
{
    private final LocationDetailsView view;

    public LocationDetailsModule(LocationDetailsView view) {
        this.view = view;
    }

    @Provides
    LocationDetailsView provideLocationDetailsView() {
        return view;
    }

    @Provides
    LocationDetailsPresenter provideLocationDetailsPresenter(LocationDetailsView view, RestDataSource restDataSource)
    {
        return new LocationDetailsPresenterImpl(view, restDataSource);
    }
}
