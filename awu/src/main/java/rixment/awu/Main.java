package rixment.awu;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Main {

    private static final String TAG = "Rixment";

    public static boolean isNetworkAvailable(Activity activity) {
        Log.i(TAG, "isNetworkAvailable");
        ConnectivityManager connectivityMgr =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityMgr == null) {
            Log.i(TAG, "getSystemService(Context.CONNECTIVITY_SERVICE) == null");
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
            Log.i(TAG, "getSystemService(Context.UI_MODE_SERVICE) == null");
            return false;
        }
        int modeType = uiModeManager.getCurrentModeType();
        return modeType == Configuration.UI_MODE_TYPE_TELEVISION;
    }

}
