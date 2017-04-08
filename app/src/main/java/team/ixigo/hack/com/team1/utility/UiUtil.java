package team.ixigo.hack.com.team1.utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

import team.ixigo.hack.com.team1.R;

public class UiUtil
{
    private static boolean isShowingGotoSetting;

    /**
     * Show AlertDialog : Location Services Turned On
     * @param context
     * @return
     */
    public static AlertDialog enableLocation(final Context context)
    {
        if(isShowingGotoSetting)
        {
            return null;
        }

        //Set Flag
        isShowingGotoSetting = true;

        //Show AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.location_services_title);
        builder.setMessage(R.string.location_services_message);
        builder.setCancelable(false);

        builder.setPositiveButton(R.string.button_enabled, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Dismiss Dialog
                dialog.dismiss();

                //Goto WiFi Settings
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);

                // Verify that the intent will resolve to an activity
                if (intent.resolveActivity(context.getPackageManager()) != null)
                {
                    context.startActivity(intent);
                }

                //Set Flag
                isShowingGotoSetting = false;
            }
        });

        builder.setNegativeButton(R.string.button_dismiss, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Dismiss Dialog
                dialog.dismiss();

                //Set Flag
                isShowingGotoSetting = false;
            }
        });

        //Get AlertDialog
        AlertDialog alertDialog = builder.create();

        //Show AlertDialog
        alertDialog.show();

        return alertDialog;
    }

    public static void dismissDialog(ProgressDialog dialog)
    {
        try
        {
            if (dialog != null)
            {
                dialog.dismiss();
            }
        }
        catch (Exception exception)
        {
            //Consume it
        }
    }
    public static void dismissDialog(Dialog dialog)
    {
        try
        {
            if (dialog != null)
            {
                dialog.dismiss();
            }
        }
        catch (Exception exception)
        {
            //Consume it
        }
    }
}
