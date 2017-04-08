package team.ixigo.hack.com.team1;

import android.app.Application;
import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;

public class MainApp extends Application
{
    private AppComponent component;
    private OkHttpClient okHttpClient;
    private Service searchService;

    @Override
    public void onCreate()
    {
        super.onCreate();

        //Initialize Ok Http Client
        okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        okHttpClient.setWriteTimeout(30 * 1000, TimeUnit.MILLISECONDS);

        initializeSearchService();
        initializeRmsAppComponentGraph();
    }

    public void initializeSearchService()
    {
        String baseUrl = BuildConfig.BASE_URL;

        RequestInterceptor rmsRequestInterceptor = new RequestInterceptor()
        {
            @Override
            public void intercept(RequestFacade request)
            {
                //TODO Give Header here
            }
        };

        RestAdapter.Builder adapterBuilder = new RestAdapter.Builder();
        RestAdapter adapter;

        if (BuildConfig.DEBUG)
        {
            adapter = adapterBuilder.setEndpoint(baseUrl)
                    .setErrorHandler(new ServiceErrorHandler())
                    .setRequestInterceptor(rmsRequestInterceptor)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setLog(new AndroidLog("Trip Advisor"))
                    .setClient(new OkClient(okHttpClient))
                    .build();
        }
        else
        {
            adapter = adapterBuilder.setEndpoint(baseUrl)
                    .setErrorHandler(new ServiceErrorHandler())
                    .setRequestInterceptor(rmsRequestInterceptor)
                    .setClient(new OkClient(okHttpClient))
                    .build();
        }

        searchService = adapter.create(Service.class);
    }

    /**
     * Initialize App Component Graph - Common
     */
    private void initializeRmsAppComponentGraph() {
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        component.inject(this);
    }

    public AppComponent component() {
        return component;
    }

    public Service getSearchServices() {
        return searchService;
    }

    public static MainApp get(Context context) {
        return (MainApp) context.getApplicationContext();
    }
}
