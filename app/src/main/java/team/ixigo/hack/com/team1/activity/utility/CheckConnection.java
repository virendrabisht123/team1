package team.ixigo.hack.com.team1.utility;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * Created by zopper on 24/8/16.
 */
public class CheckConnection
{
    private final Context context;

    public CheckConnection(Context context)
    {
        this.context = context;
    }

    @SuppressWarnings("deprecation")
    @TargetApi(23)
    public boolean isConnectingToInternet()
    {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            final int version = Build.VERSION.SDK_INT;
            if (version >= Build.VERSION_CODES.M)
            {
                Network[] networks = connectivity.getAllNetworks();
                if(networks != null)
                {
                    for(Network network: networks)
                    {
                        NetworkInfo info = connectivity.getNetworkInfo(network);
                        if((info != null) && info.getState() == NetworkInfo.State.CONNECTED)
                            return true;
                    }
                }
            }
            else
            {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                {
                    for(NetworkInfo networkInfo : info)
                    {
                        if (networkInfo.getState() == NetworkInfo.State.CONNECTED)
                            return true;
                    }
                }
            }
        }
        return false;
    }
}
