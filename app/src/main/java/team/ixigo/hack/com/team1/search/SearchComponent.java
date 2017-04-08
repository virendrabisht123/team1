package team.ixigo.hack.com.team1.search;

import dagger.Component;
import team.ixigo.hack.com.team1.ActivityScope;
import team.ixigo.hack.com.team1.AppComponent;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                SearchModule.class
        }
)
public interface SearchComponent
{
    void inject(SearchActivity activity);

    SearchPresenter provideSearchPresenter();
}
