package team.ixigo.hack.com.team1.utility;

import android.content.Context;
import android.database.Cursor;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.Collection;

/**
 * Created by zopper on 24/8/16.
 */
public class AppUtil
{
    /**
     * To check if Android version is 3.0 and above
     * @return True if Android version is 3.0 and above else False
     */
    public static boolean isCurrentVersionHoneycombAndAbove()
    {
        try
        {
            int currentapiVersion = android.os.Build.VERSION.SDK_INT;

            if (currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }

    //Check Collection for Empty
    public static boolean isCollectionEmpty(Collection<? extends Object> collection)
    {
        if(collection == null || collection.isEmpty())
        {
            return true;
        }

        return false;
    }

    /**
     * Check String Empty
     *
     * @param stringRes
     * @return
     */
    //Is String Empty
    public static boolean isStringEmpty(String stringRes)
    {
        if(stringRes == null || stringRes.trim().isEmpty())
        {
            return true;
        }

        return false;
    }

    /**
     * Check Google Play Services
     * @param context
     * @return isGooglePlayServicesAvailable
     */
    public static boolean isGooglePlayServicesAvailable(Context context)
    {
        boolean isGooglePlayServicesAvailable = false;

        // Getting Google Play Availability Status
        int isGooglePlayServicesResultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);

        //Google Play Services are not available
        if(isGooglePlayServicesResultCode == ConnectionResult.SUCCESS)
        {
            isGooglePlayServicesAvailable = true;
        }
        else
        {
            isGooglePlayServicesAvailable = false;
        }

        return isGooglePlayServicesAvailable;
    }

    //Set String Not NULL
    public static String setStringNotNull(String stringRes)
    {
        if(stringRes == null)
        {
            return  "";
        }

        //Convert Non_breaking (ASCI 160) to Simple Space. trim() does'nt handle it.
        stringRes = stringRes.replace(String.valueOf((char) 160), " ");
        stringRes = stringRes.trim();

        return stringRes;
    }

    /***
     * Close cursor
     * @param cursor
     */
    public static void closeCursor(Cursor cursor)
    {
        if (cursor != null && !cursor.isClosed())
        {
            try
            {
                cursor.close();
            }
            catch (Exception e)
            {
                //Do Nothing
                e.printStackTrace();
            }
        }
    }
}
