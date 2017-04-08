package team.ixigo.hack.com.team1.utility;

import android.content.SharedPreferences;

/**
 * Persistent Manager - Methods to Read/Write from/to Shared-Preferences
 *
 */
public class PersistentUtil
{
	/**
	 * Get Call Permission
	 *
	 * @return boolean
	 */
	public static boolean getLocationPermission(SharedPreferences sharedPreferences)
	{
		return (Boolean) getContentFromSharedPreferences(Constants.LOCATION_PERMISSION, Boolean.class, sharedPreferences);
	}

	//Get Call Permission
	public static void persistLocationPermission(boolean isCallPermissionAsked, SharedPreferences sharedPreferences)
	{
		writeContentToSharedPreferences(Constants.LOCATION_PERMISSION, isCallPermissionAsked, sharedPreferences);
	}

	/**
	 * Get Is First Time
	 *
	 * @return boolean
	 */
	public static boolean getIsFirstTime(SharedPreferences sharedPreferences)
	{
		return (Boolean) getContentFromSharedPreferences(Constants.IS_FIRST_TIME, Boolean.class, sharedPreferences);
	}

	//Get Is First Time
	public static void persistIsFirstTime(boolean isFirstTime, SharedPreferences sharedPreferences)
	{
		writeContentToSharedPreferences(Constants.IS_FIRST_TIME, isFirstTime, sharedPreferences);
	}

	/**
	 * Common Functions
	 */
	//Write Content to File
	private static void writeContentToSharedPreferences(String key, Object value, SharedPreferences sharedPreferences)
	{
		//Get SharedPreferences Editor
		SharedPreferences.Editor editor = sharedPreferences.edit();

		//Write content to File
		if(value instanceof String) //String value
		{
			editor.putString(key, ((String)value).trim());
		}
		else if(value instanceof Boolean) //Boolean value
		{
			editor.putBoolean(key, ((Boolean)value));
		}
		else if(value instanceof Float) //Float value
		{
			editor.putFloat(key, ((Float)value));
		}
		else if(value instanceof Integer) //Integer value
		{
			editor.putInt(key, ((Integer)value));
		}
		else if(value instanceof Long) //Long value
		{
			editor.putLong(key, ((Long)value));
		}

		//Commit Preferences Changes
		editor.apply();
	}

	//Get Content from File
	private static Object getContentFromSharedPreferences(String key, Class<? extends Object> classType, SharedPreferences sharedPreferences)
	{
		Object object;

		//Get Content from File
		if(classType.equals(String.class)) //String value
		{
			object = sharedPreferences.getString(key, "");
		}
		else if(classType.equals(Boolean.class)) //Boolean value
		{
			object = sharedPreferences.getBoolean(key, false);
		}
		else if(classType.equals(Integer.class)) //Integer value
		{
			object = sharedPreferences.getInt(key, 0);
		}
		else if(classType.equals(Float.class)) //Float value
		{
			object = sharedPreferences.getFloat(key, 0.00f);
		}
		else if(classType.equals(Long.class)) //Long value
		{
			object = sharedPreferences.getLong(key, 0L);
		}
		else
		{
			object = null;
		}

		return object;
	}
}
