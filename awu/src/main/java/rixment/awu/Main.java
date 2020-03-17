package rixment.awu;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Main {

    private static final String TAG = "rixment.awu";

    public static boolean isNetworkAvailable(Activity activity) {
        Log.i(TAG, "isNetworkAvailable");
        ConnectivityManager connectivityMgr =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityMgr == null) {
            Log.w(TAG, "getSystemService(Context.CONNECTIVITY_SERVICE) == null");
            return true;
        }
        NetworkInfo activeNetwork = connectivityMgr.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isAndroidTv(Activity activity) {
        Log.i(TAG, "isAndroidTv");
        UiModeManager uiModeManager =
                (UiModeManager) activity.getSystemService(Context.UI_MODE_SERVICE);
        if(uiModeManager == null) {
            Log.w(TAG, "getSystemService(Context.UI_MODE_SERVICE) == null");
            return false;
        }
        int modeType = uiModeManager.getCurrentModeType();
        return modeType == Configuration.UI_MODE_TYPE_TELEVISION;
    }

    public static void shareText(Activity activity, String title, String subject, String text) {
        try {
            Log.i(TAG, "shareText");
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(android.content.Intent.EXTRA_TEXT, text);
            intent.setType("text/plain");
            activity.startActivity(Intent.createChooser(intent, title));
        } catch (ActivityNotFoundException e) {
            String msg = e.getMessage();
            Log.w(TAG, msg != null ? msg : "shareText ActivityNotFoundException thrown");
        }
    }

}
