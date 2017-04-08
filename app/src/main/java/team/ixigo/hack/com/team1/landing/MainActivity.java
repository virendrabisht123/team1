package team.ixigo.hack.com.team1.landing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
import team.ixigo.hack.com.team1.locationdetails.LocationDetailsActivity;
import team.ixigo.hack.com.team1.model.response.RecommendedListResponse;
import team.ixigo.hack.com.team1.search.SearchActivity;
import team.ixigo.hack.com.team1.utility.AppUtil;
import team.ixigo.hack.com.team1.utility.CheckConnection;
import team.ixigo.hack.com.team1.utility.Constants;

public class MainActivity extends BaseActivity implements HomeView, View.OnClickListener, HomeRecommendedPlacesAdapter.HomeRecommendedPlacesSectionClickListener
{
    @Inject
    HomePresenter homePresenter;

    @Inject
    CheckConnection checkConnection;

    @Inject
    MainApp mainApp;

    @Bind(R.id.textViewSearch)
    TextView textViewSearch;
    @Bind(R.id.relativeLayoytTop)
    RelativeLayout relativeLayoytTop;
    @Bind(R.id.recyclerViewRecommended1)
    RecyclerView recyclerViewRecommended1;
    @Bind(R.id.recyclerViewRecommended2)
    RecyclerView recyclerViewRecommended2;

    @Bind(R.id.relativeLayoutProgressBar)
    RelativeLayout relativeLayoutProgressBar;
    @Bind(R.id.relativeLayoutErrorOccured)
    RelativeLayout relativeLayoutErrorOccured;
    @Bind(R.id.textViewError)
    TextView textViewError;
    @Bind(R.id.editTextSearchLocation)
    EditText editTextSearchLocation;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.textViewTitle)
    TextView textViewTitle;
    @Bind(R.id.imageButtonBackPress)
    ImageButton imageButtonBackPress;
    @Bind(R.id.textViewMessage)
    TextView textViewMessage;
    @Bind(R.id.relativeLayoutRecommendedPlaces)
    RelativeLayout relativeLayoutRecommendedPlaces;

    private HomeRecommendedPlacesAdapter homeRecommendedPlacesAdapter;
    private HomeRecommendedPlacesAdapter homeRecommendedPlacesAdapter1;
    private ArrayList<RecommendedListResponse.Data.RecommendedItems> recommendedItems1;
    private ArrayList<RecommendedListResponse.Data.RecommendedItems> recommendedItems2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initializeView();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        initialData();
    }

    @Override
    protected void setupComponent(AppComponent component) {
        DaggerHomeComponent.builder()
                .appComponent(component)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    private void initializeView()
    {
        BackButtonClickListener backButtonClickListener = new BackButtonClickListener();
        imageButtonBackPress.setOnClickListener(backButtonClickListener);

        setToolBar(toolbar, textViewTitle, getResources().getString(R.string.title_home_screen), false, false);

        recommendedItems1 = new ArrayList<RecommendedListResponse.Data.RecommendedItems>();
        recommendedItems2 = new ArrayList<RecommendedListResponse.Data.RecommendedItems>();

        LinearLayoutManager recommendedLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecommended1.setLayoutManager(recommendedLinearLayoutManager);

        homeRecommendedPlacesAdapter = new HomeRecommendedPlacesAdapter(this, recommendedItems1, this, HomeRecommendedPlacesAdapter.TYPE_FLIGHT);
        recyclerViewRecommended1.setAdapter(homeRecommendedPlacesAdapter);

        LinearLayoutManager buyersDemandLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecommended2.setLayoutManager(buyersDemandLinearLayoutManager);

        homeRecommendedPlacesAdapter1 = new HomeRecommendedPlacesAdapter(this, recommendedItems2, this, HomeRecommendedPlacesAdapter.TYPE_BUDGET_FLIGHT);
        recyclerViewRecommended2.setAdapter(homeRecommendedPlacesAdapter1);

        textViewSearch.setOnClickListener(this);
        textViewError.setOnClickListener(this);
    }

    private void initialData()
    {
        hideView();

        recommendedItems2.clear();
        recommendedItems1.clear();
        homeRecommendedPlacesAdapter1.notifyDataSetChanged();
        homeRecommendedPlacesAdapter.notifyDataSetChanged();

        getRecommendedLocationData();
    }

    @Override
    public void getRecommendedListSuccess(RecommendedListResponse response, Response retrofitResponse)
    {
        showView();

        recommendedItems1.clear();
        recommendedItems2.clear();

        recommendedItems1.addAll(response.getData().getFlight());
        recommendedItems2.addAll(response.getData().getBudget_flight());

        homeRecommendedPlacesAdapter.notifyDataSetChanged();
        homeRecommendedPlacesAdapter1.notifyDataSetChanged();

        if(AppUtil.isCollectionEmpty(recommendedItems1) && AppUtil.isCollectionEmpty(recommendedItems2))
        {
            relativeLayoutErrorOccured.setVisibility(View.VISIBLE);
            relativeLayoutRecommendedPlaces.setVisibility(View.GONE);
            textViewError.setVisibility(View.GONE);
            textViewMessage.setText(getResources().getString(R.string.no_data_found));
        }
    }

    @Override
    public void getRecommendedListError(RetrofitError error)
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

    private void getRecommendedLocationData()
    {
        showView();
        homePresenter.getRecommendedList(Constants.PRODUCT_TYPE, Constants.API_KEY, checkConnection, mainApp.getSearchServices());
    }

    @Override
    public void onClick(View view)
    {
        int viewId = view.getId();
        switch(viewId)
        {
            case R.id.textViewError:
                handleWhenErrorOccured();
                break;
            case R.id.textViewSearch:
                handleNavigationToSearchActivity();
                break;
        }
    }

    private void handleNavigationToSearchActivity()
    {
        String queryText = editTextSearchLocation.getText().toString();
        if(!AppUtil.isStringEmpty(queryText))
        {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putExtra(Constants.DATA, queryText);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, getResources().getString(R.string.validation_search_location), Toast.LENGTH_SHORT).show();
        }
    }

    private void handleWhenErrorOccured()
    {
        initialData();
    }

    @Override
    public void setOnClickListener(int position, int typeName) {

        String cityId = null;
        if(typeName == HomeRecommendedPlacesAdapter.TYPE_FLIGHT)
        {
            cityId = recommendedItems1.get(position).getCityId();
        }
        else if(typeName == HomeRecommendedPlacesAdapter.TYPE_BUDGET_FLIGHT)
        {
            cityId = recommendedItems2.get(position).getCityId();
        }

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
        relativeLayoutErrorOccured.setVisibility(View.GONE);
        relativeLayoutRecommendedPlaces.setVisibility(View.VISIBLE);
    }

    private void hideView()
    {
        relativeLayoutErrorOccured.setVisibility(View.VISIBLE);
        relativeLayoutRecommendedPlaces.setVisibility(View.GONE);
    }
}
