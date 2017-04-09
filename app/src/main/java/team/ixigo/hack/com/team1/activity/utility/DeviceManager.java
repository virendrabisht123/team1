package team.ixigo.hack.com.team1.utility;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Methods to Get Device Information or Performing Device Specific Operations.
 *  
 * @author Virendra Pal Singh
 *
 */
public class DeviceManager 
{
	/************ Network Related Operations *****************/

	/**
	 * Check Network Connection
	 * 
	 * @return
	 */
	public static boolean isNetworkAvailable(Context appContext)
	{
		//Get ConnectivityManager
		ConnectivityManager connectivityManager = (ConnectivityManager)appContext.getSystemService(Context.CONNECTIVITY_SERVICE);

		//Get NetworkInfo
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		return ((networkInfo == null || !networkInfo.isConnected()) ? false : true);
	}

	/************* Location Services Related **************/

	/**
	 * Is Location Service Available
	 * @param context
	 * @return
	 */
	public static boolean isLocationServiceAvailable(Context context)
	{
		boolean isLocationServiceAvailable = false;

		//Get Location Manager
		LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

		if(locationManager != null)
		{
			try 
			{
				if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
				{
					isLocationServiceAvailable = true;
				}
				else 
				{
					isLocationServiceAvailable = false;
				}
			}
			catch (Exception exception) 
			{
				isLocationServiceAvailable = false;
			}
		}

		return isLocationServiceAvailable;
	}
}
