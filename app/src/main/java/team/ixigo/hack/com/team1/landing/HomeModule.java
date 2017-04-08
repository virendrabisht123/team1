package team.ixigo.hack.com.team1.landing;

import dagger.Module;
import dagger.Provides;
import team.ixigo.hack.com.team1.RestDataSource;

@Module
public class HomeModule
{
    private final HomeView view;

    public HomeModule(HomeView view) {
        this.view = view;
    }

    @Provides
    HomeView provideRecommendedView() {
        return view;
    }

    @Provides
    HomePresenter provideRecommendedPresenter(HomeView view, RestDataSource restDataSource)
    {
        return new HomePresenterImpl(view, restDataSource);
    }
}
