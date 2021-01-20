package rixment.awu;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.TransportInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.Objects;

public class Main {

    private static final String TAG = "rixment.awu";

    private static ConnectivityManager getConnectivityManager(Activity activity) {
        Log.d(TAG, "getConnectivityManager");
        ConnectivityManager mgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mgr == null) {
            Log.w(TAG, "getSystemService(Context.CONNECTIVITY_SERVICE) == null");
            throw new NullPointerException();
        }
        return mgr;
    }

    public static boolean isNetworkAvailable(Activity activity) {
        Log.d(TAG, "isNetworkAvailable");
        try {
            ConnectivityManager connectivityMgr = getConnectivityManager(activity);
            NetworkInfo activeNetwork = connectivityMgr.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
        catch(NullPointerException e) {
            return false;
        }
}

    public static boolean isAndroidTv(Activity activity) {
        Log.d(TAG, "isAndroidTv");
        UiModeManager uiModeManager =
                (UiModeManager) activity.getSystemService(Context.UI_MODE_SERVICE);
        if(uiModeManager == null) {
            Log.w(TAG, "getSystemService(Context.UI_MODE_SERVICE) == null");
            return false;
        }
        int modeType = uiModeManager.getCurrentModeType();
        return modeType == Configuration.UI_MODE_TYPE_TELEVISION;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static boolean isConnectedVia(Activity activity, int transportType) {
        try {
            ConnectivityManager connectivityMgr = getConnectivityManager(activity);
            Network network = connectivityMgr.getActiveNetwork();
            NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();
            return network != null &&
                    networkInfo != null &&
                    networkInfo.isConnected() &&
                    Objects.requireNonNull(connectivityMgr.getNetworkCapabilities(network)).hasTransport(transportType);
        } catch(NullPointerException e) {
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isConnectedViaCellular(Activity activity) {
        Log.d(TAG, "isConnectedViaCellular");
        return isConnectedVia(activity, NetworkCapabilities.TRANSPORT_CELLULAR);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isConnectedViaWifi(Activity activity) {
        Log.d(TAG, "isConnectedViaWifi");
        return isConnectedVia(activity, NetworkCapabilities.TRANSPORT_WIFI);
    }

    public static void shareText(Activity activity, String chooserTitle, String subject, String text) {
        try {
            Log.d(TAG, "shareText");
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            intent.setType("text/plain");
            activity.startActivity(Intent.createChooser(intent, chooserTitle));
        } catch (ActivityNotFoundException e) {
            String msg = e.getMessage();
            Log.w(TAG, msg != null ? msg : "shareText ActivityNotFoundException thrown");
        }
    }

    public static void shareFile(Activity activity, String chooserTitle, String subject, String text, String absoluteFileName, String fileType) {
        try {
            Log.d(TAG, "shareFile");
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(absoluteFileName)));
            intent.setType(fileType);
            activity.startActivity(Intent.createChooser(intent, chooserTitle));
        } catch (ActivityNotFoundException e) {
            String msg = e.getMessage();
            Log.w(TAG, msg != null ? msg : "shareFile ActivityNotFoundException thrown");
        }
    }

}
