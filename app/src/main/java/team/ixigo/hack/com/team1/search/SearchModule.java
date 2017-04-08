package team.ixigo.hack.com.team1.search;

import dagger.Module;
import dagger.Provides;
import team.ixigo.hack.com.team1.RestDataSource;

@Module
public class SearchModule
{
    private final SearchView view;

    public SearchModule(SearchView view) {
        this.view = view;
    }

    @Provides
    SearchView provideSearchView() {
        return view;
    }

    @Provides
    SearchPresenter provideSearchPresenter(SearchView view, RestDataSource restDataSource)
    {
        return new SearchPresenterImpl(view, restDataSource);
    }
}
