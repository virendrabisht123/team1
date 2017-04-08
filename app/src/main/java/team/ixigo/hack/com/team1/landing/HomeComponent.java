package team.ixigo.hack.com.team1.landing;

import dagger.Component;
import team.ixigo.hack.com.team1.ActivityScope;
import team.ixigo.hack.com.team1.AppComponent;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                HomeModule.class
        }
)
public interface HomeComponent
{
    void inject(MainActivity activity);

    HomePresenter provideSearchPresenter();
}
