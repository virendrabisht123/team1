package team.ixigo.hack.com.team1.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit.client.Response;
import team.ixigo.hack.com.team1.AppComponent;
import team.ixigo.hack.com.team1.MainApp;
import team.ixigo.hack.com.team1.R;
import team.ixigo.hack.com.team1.activity.BaseActivity;
import team.ixigo.hack.com.team1.adapter.SearchPlacesAdapter;
import team.ixigo.hack.com.team1.model.response.SearchResponse;
import team.ixigo.hack.com.team1.utility.AppUtil;
import team.ixigo.hack.com.team1.utility.CheckConnection;
import team.ixigo.hack.com.team1.utility.Constants;

public class SearchActivity extends BaseActivity implements SearchView, View.OnClickListener, SearchPlacesAdapter.SearchPlacesSectionClickListener
{
    @Inject
    SearchPresenter searchPresenter;

    @Inject
    CheckConnection checkConnection;

    @Inject
    MainApp mainApp;

    @Bind(R.id.recyclerViewSearch)
    RecyclerView recyclerViewRecommended1;
    @Bind(R.id.textViewSearchList)
    TextView textViewSearchList;
    @Bind(R.id.relativeLayoutProgressBar)
    RelativeLayout relativeLayoutProgressBar;
    @Bind(R.id.relativeLayoutErrorOccured)
    RelativeLayout relativeLayoutErrorOccured;
    @Bind(R.id.textViewError)
    TextView textViewError;

    private SearchPlacesAdapter searchPlacesAdapter;
    private ArrayList<SearchResponse> listArrayList;
    private String searchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity_layout);
        ButterKnife.bind(this);

        initializeView();
        initialData();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void setupComponent(AppComponent component) {
        DaggerSearchComponent.builder()
                .appComponent(component)
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
    }

    private void initializeView()
    {
        listArrayList = new ArrayList<SearchResponse>();

        LinearLayoutManager recommendedLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewRecommended1.setLayoutManager(recommendedLinearLayoutManager);

        searchPlacesAdapter = new SearchPlacesAdapter(this, listArrayList, this);
        recyclerViewRecommended1.setAdapter(searchPlacesAdapter);
    }

    private void initialData()
    {
        Intent intent = getIntent();
        searchQuery = intent.getStringExtra(Constants.DATA);

        getRecommendedLocationData(searchQuery);
    }

    @Override
    public void getSearchListSuccess(List<SearchResponse> response, Response retrofitResponse)
    {
        relativeLayoutErrorOccured.setVisibility(View.GONE);

        listArrayList.clear();

        if(!AppUtil.isCollectionEmpty(response))
        {
            listArrayList.addAll(response);

            searchPlacesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSearchListError(RetrofitError error)
    {

    }

    @Override
    public void showProgressBar()
    {
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar()
    {
        relativeLayoutProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToastMessage()
    {
        Toast.makeText(this, getResources().getString(R.string.network_issues), Toast.LENGTH_SHORT).show();
    }

    private void getRecommendedLocationData(String query)
    {
        searchPresenter.getSearchList(Constants.SEARCH_FOR, Constants.NEW_CATEGORY, query, checkConnection, mainApp.getSearchServices());
    }

    @Override
    public void onClick(View view)
    {

    }

    @Override
    public void setOnClickListener(int position, String typeName)
    {

    }
}
