package team.ixigo.hack.com.team1.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import team.ixigo.hack.com.team1.AppComponent;
import team.ixigo.hack.com.team1.MainApp;
import team.ixigo.hack.com.team1.R;

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
        int id = item.getItemId();

        switch (id)
        {
            case android.R.id.home:
            {
                finish();

                return true;
            }
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

    /**
     * This method will set toolbar.
     */
    public void setToolBar(Toolbar toolbar, TextView textViewTitle, String title, boolean showBackButton, boolean showHomeButton)
    {
        int textColor = ContextCompat.getColor(this, R.color.black);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            toolbar.setTitleTextColor(textColor);

            if(!title.isEmpty())
            {
                toolbar.setTitle(title);
                textViewTitle.setText(title);
            }

            if(showBackButton)
            {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }

            if(showHomeButton)
            {
                //TODO Change Image Here
                //getSupportActionBar().setHomeAsUpIndicator(R.drawable.header);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    public class BackButtonClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            int viewId = view.getId();

            switch(viewId)
            {
                case R.id.imageButtonBackPress:
                    handleBackButton();
                    break;
            }
        }
    }

    public void  handleBackButton()
    {
        finish();
    }

    //Create Abstract Method for initializing Activity References
    protected abstract void setupComponent(AppComponent component);
}
