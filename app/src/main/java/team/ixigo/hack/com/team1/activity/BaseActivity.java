package team.ixigo.hack.com.team1.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import team.ixigo.hack.com.team1.AppComponent;
import team.ixigo.hack.com.team1.MainApp;

/**
 * Created by virendrapalsingh on 8/4/17.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Setup Component
        setupComponent(MainApp.get(this).component());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home)
        {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set Action Bar
     * @param drawable
     * @param title
     * @param isHomeEnabled
     */
    protected void setActionBar(Drawable drawable, String title, boolean isHomeEnabled)
    {
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null)
        {
            actionBar.setBackgroundDrawable(drawable);
            actionBar.setTitle(title);

            if(isHomeEnabled)
            {
                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    //Create Abstract Method for initializing Activity References
    protected abstract void setupComponent(AppComponent component);
}
