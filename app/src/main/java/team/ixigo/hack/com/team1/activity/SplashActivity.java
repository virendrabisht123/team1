package team.ixigo.hack.com.team1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import team.ixigo.hack.com.team1.R;
import team.ixigo.hack.com.team1.landing.MainActivity;
import team.ixigo.hack.com.team1.utility.Constants;

/**
 * Created by virendrapalsingh on 8/4/17.
 */

public class SplashActivity extends AppCompatActivity
{
    private Handler handler;
    private SplashRunnable splashRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_layout);

        initializeView();
    }

    //InitializeView
    private void initializeView()
    {
        //Create Handler Instance
        handler = new Handler();

        //Create Splash Runnable Inner Class Instance
        splashRunnable = new SplashRunnable();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //Splash Time Interval
        handler.postDelayed(splashRunnable, Constants.TIME_SPLASH_DELAY_IN_MILLISEC);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        //Remove Pending Task
        handler.removeCallbacks(splashRunnable);
    }

    /**
     * Navigate to Next Screen
     */
    private void identifyNavigation()
    {
        Intent intent = new Intent(this, MainActivity.class);

        //Navigate to the next Activity
        startActivity(intent);
        finish();
    }

    /**
     * Splash Runnable Thread Class
     * @author Virendra Pal Singh
     *
     */
    class SplashRunnable implements Runnable
    {
        @Override
        public void run()
        {
            //Testing
            Log.i(Constants.LOG_TAG, "Splash Timeout Over");

            //Navigate to Next Screen
            identifyNavigation();
        }
    }
}
