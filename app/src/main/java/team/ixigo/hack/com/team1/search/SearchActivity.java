package team.ixigo.hack.com.team1.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
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
import team.ixigo.hack.com.team1.adapter.HomeRecommendedPlacesAdapter;
import team.ixigo.hack.com.team1.adapter.SearchPlacesAdapter;
import team.ixigo.hack.com.team1.locationdetails.LocationDetailsActivity;
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
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textViewTitle)
    TextView textViewTitle;
    @Bind(R.id.imageButtonBackPress)
    ImageButton imageButtonBackPress;
    @Bind(R.id.textViewMessage)
    TextView textViewMessage;

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
        BackButtonClickListener backButtonClickListener = new BackButtonClickListener();
        imageButtonBackPress.setOnClickListener(backButtonClickListener);
        setToolBar(toolbar, textViewTitle, getResources().getString(R.string.title_search_screen), false, false);

        listArrayList = new ArrayList<SearchResponse>();

        LinearLayoutManager recommendedLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewRecommended1.setLayoutManager(recommendedLinearLayoutManager);

        searchPlacesAdapter = new SearchPlacesAdapter(this, listArrayList, this);
        recyclerViewRecommended1.setAdapter(searchPlacesAdapter);
        textViewError.setOnClickListener(this);
    }

    private void initialData()
    {
        hideView();

        Intent intent = getIntent();
        searchQuery = intent.getStringExtra(Constants.DATA);

        getRecommendedLocationData(searchQuery);
    }

    @Override
    public void getSearchListSuccess(List<SearchResponse> response, Response retrofitResponse)
    {
        showView();

        listArrayList.clear();

        if(!AppUtil.isCollectionEmpty(response))
        {
            listArrayList.addAll(response);

            searchPlacesAdapter.notifyDataSetChanged();
        }
        else
        {
            textViewSearchList.setVisibility(View.GONE);
            relativeLayoutErrorOccured.setVisibility(View.VISIBLE);
            recyclerViewRecommended1.setVisibility(View.GONE);
            textViewError.setVisibility(View.GONE);
            textViewMessage.setText(getResources().getString(R.string.no_data_found));
        }
    }

    @Override
    public void getSearchListError(RetrofitError error)
    {
        handleWhenSomeProblemOccured(getResources().getString(R.string.some_problem_occured));
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
        handleWhenSomeProblemOccured(getResources().getString(R.string.network_issues));
        Toast.makeText(this, getResources().getString(R.string.network_issues), Toast.LENGTH_SHORT).show();
    }

    private void getRecommendedLocationData(String query)
    {
        showView();

        searchPresenter.getSearchList(Constants.SEARCH_FOR, Constants.NEW_CATEGORY, query, checkConnection, mainApp.getSearchServices());
    }

    @Override
    public void onClick(View view)
    {
        getRecommendedLocationData(searchQuery);
    }

    @Override
    public void setOnClickListener(int position, String typeName)
    {
        String cityId = null;
        cityId = listArrayList.get(position).getCityId();

        Intent intent = new Intent(this, LocationDetailsActivity.class);
        intent.putExtra(Constants.CITY_ID, cityId);
        startActivity(intent);
    }

    private void handleWhenSomeProblemOccured(String message)
    {
        textViewMessage.setText(message);
        hideView();
    }

    private void showView()
    {
        textViewSearchList.setVisibility(View.VISIBLE);
        relativeLayoutErrorOccured.setVisibility(View.GONE);
        recyclerViewRecommended1.setVisibility(View.VISIBLE);
    }

    private void hideView()
    {
        textViewSearchList.setVisibility(View.GONE);
        relativeLayoutErrorOccured.setVisibility(View.VISIBLE);
        recyclerViewRecommended1.setVisibility(View.GONE);
    }
}
