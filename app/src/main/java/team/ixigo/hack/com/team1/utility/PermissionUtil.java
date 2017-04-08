package team.ixigo.hack.com.team1.utility;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class PermissionUtil
{
    public static final int PERMISSION_ONE = 0;

    /**
     * Request Location Permission
     * @param activity
     * @param requestCallback
     * @return boolean
     */
    public static boolean requestLocationPermission(Activity activity, int requestCallback, boolean isFirstTime, String message)
    {
        //Request For Permission
        return requestPermission(activity, requestCallback, Manifest.permission.ACCESS_FINE_LOCATION, isFirstTime, message);
    }

    /**
     * Request Permission  - Show Permission Dialog To Ask For Permission Granted From The User
     * @param activity
     * @param requestCallback
     * @param permission
     * @return boolean
     */
    private static boolean requestPermission(Activity activity, int requestCallback, String permission, boolean isFirstTime, String message)
    {
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED)
        {
            //It gives true when application denied Permission -
            boolean shouldShowRequestPermissionRational = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);

            if (!shouldShowRequestPermissionRational && isFirstTime) // Check For First Time To Ask Permission And When User Check Do Not Show Dialog Again
            {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }

            //Request Permission Dialog
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCallback);

            return false;
        }

        return true;
    }
}
