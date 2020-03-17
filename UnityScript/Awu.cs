using UnityEngine;

public class Awu
{
    public static bool IsNetworkAvailable
    {
        get
        {
#if UNITY_EDITOR || !UNITY_ANDROID
            return true;
#else
            AndroidJavaClass connectionClass = new AndroidJavaClass("rixment.awu.Main");
            AndroidJavaClass unity = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            AndroidJavaObject currentActivity = unity.GetStatic<AndroidJavaObject>("currentActivity");

            return connectionClass.CallStatic<bool>("isNetworkAvailable", currentActivity);
#endif
        }
    }

    public static bool IsAndroidTV
    {
        get
        {
#if UNITY_EDITOR || !UNITY_ANDROID
            return false;
#else
            AndroidJavaClass connectionClass = new AndroidJavaClass("rixment.awu.Main");
            AndroidJavaClass unity = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
            AndroidJavaObject currentActivity = unity.GetStatic<AndroidJavaObject>("currentActivity");

            return connectionClass.CallStatic<bool>("isAndroidTv", currentActivity);
#endif
        }
    }    
}
