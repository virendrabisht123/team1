package team.ixigo.hack.com.team1.locationdetails;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit.client.Response;
import team.ixigo.hack.com.team1.AppComponent;
import team.ixigo.hack.com.team1.MainApp;
import team.ixigo.hack.com.team1.R;
import team.ixigo.hack.com.team1.activity.BaseActivity;
import team.ixigo.hack.com.team1.model.response.SearchLocationDetailsResponse;
import team.ixigo.hack.com.team1.utility.AppUtil;
import team.ixigo.hack.com.team1.utility.CheckConnection;
import team.ixigo.hack.com.team1.utility.Constants;

public class LocationDetailsActivity extends BaseActivity implements LocationDetailsView, OnMapReadyCallback
{
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Inject
    LocationDetailsPresenter searchPresenter;

    @Inject
    CheckConnection checkConnection;

    @Inject
    MainApp mainApp;

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
    private GoogleMap mMap;
    private Marker mCurrLocationMarker;

    @Bind(R.id.textViewCountryName)
    TextView textViewCountryName;
    @Bind(R.id.imageViewLocationDetails)
    ImageView imageViewLocationDetails;
    @Bind(R.id.textViewName)
    TextView textViewName;
    @Bind(R.id.textViewStateName)
    TextView textViewStateName;
    @Bind(R.id.textViewLocationDescription)
    TextView textViewLocationDescription;
    @Bind(R.id.textViewLocationDescriptionValue)
    TextView textViewLocationDescriptionValue;

    private SearchLocationDetailsResponse searchLocationDetailsResponse;
    private String cityId;
    private  SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_details_activity_layout);
        ButterKnife.bind(this);

        initializeView();
        handleGoogleMap();
        initialData();
    }

    private void handleGoogleMap()
    {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public boolean checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
            else
            {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_LOCATION:
            {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        mMap.setMyLocationEnabled(true);
                    }

                }
                else
                {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void setupComponent(AppComponent component) {
        DaggerLocationDetailsComponent.builder()
                .appComponent(component)
                .locationDetailsModule(new LocationDetailsModule(this))
                .build()
                .inject(this);
    }

    private void initializeView()
    {
        BackButtonClickListener backButtonClickListener = new BackButtonClickListener();
        imageButtonBackPress.setOnClickListener(backButtonClickListener);
        setToolBar(toolbar, textViewTitle, getResources().getString(R.string.title_search_details), false, false);
    }

    private void initialData()
    {
        Intent intent = getIntent();
        cityId = intent.getStringExtra(Constants.CITY_ID);

        getSearchLocationDetailsData(cityId);
    }

    @Override
    public void getSearchDetailsSuccess(SearchLocationDetailsResponse response, Response retrofitResponse) {
        relativeLayoutErrorOccured.setVisibility(View.GONE);

        if(response != null)
        {
            setDataOnViews(response);
        }
    }

    @Override
    public void getSearchDetailsError(RetrofitError error) {

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

    private void getSearchLocationDetailsData(String cityId)
    {
        searchPresenter.getLocationDetails(cityId, Constants.API_KEY, checkConnection, mainApp.getSearchServices());
    }

    private void setDataOnViews(SearchLocationDetailsResponse response)
    {
        double latitude = 0.0d;
        double longitude = 0.0d;

        SearchLocationDetailsResponse.SearchDetails data = response.getData();
        if(data != null)
        {
            latitude = AppUtil.convertInDouble(data.getLatitude());
            longitude = AppUtil.convertInDouble(data.getLongitude());

            handleSetlocation(latitude, longitude);
            textViewCountryName.setText(getResources().getString(R.string.lable_country_name) + " " + data.getCountryName());
            textViewName.setText(getResources().getString(R.string.lable_city_name) + " " + data.getName());
            textViewStateName.setText(getResources().getString(R.string.lable_state_name) + " " + data.getStateName());

            if(!AppUtil.isStringEmpty(data.getDescription()))
            {
                textViewLocationDescriptionValue.setText(Html.fromHtml(data.getDescription()));
            }

            if(!AppUtil.isStringEmpty(data.getKeyImageUrl()))
            {
                Picasso.with(this).load(data.getKeyImageUrl()).into(imageViewLocationDetails);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this,  Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                mMap.setMyLocationEnabled(true);
            }
        }
        else
        {
            mMap.setMyLocationEnabled(true);
        }
    }

    private void handleSetlocation(double latitude, double longitude)
    {
        if (mCurrLocationMarker != null)
        {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
    }
}
