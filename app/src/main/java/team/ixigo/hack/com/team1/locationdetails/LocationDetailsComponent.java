package team.ixigo.hack.com.team1.locationdetails;

import dagger.Component;
import team.ixigo.hack.com.team1.ActivityScope;
import team.ixigo.hack.com.team1.AppComponent;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                LocationDetailsModule.class
        }
)
public interface LocationDetailsComponent
{
    void inject(LocationDetailsActivity activity);

    LocationDetailsPresenter provideLocationDetailsPresenter();
}
