using UnityEngine;

public static class Awu
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

    public static bool IsConnectedViaCellular
    {
        get
        {
#if UNITY_EDITOR || !UNITY_ANDROID
        return false;
#else
        AndroidJavaClass connectionClass = new AndroidJavaClass("rixment.awu.Main");
        AndroidJavaClass unity = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject currentActivity = unity.GetStatic<AndroidJavaObject>("currentActivity");

        return connectionClass.CallStatic<bool>("isConnectedViaCellular", currentActivity);
#endif
        }
    }

    public static bool IsConnectedViaWifi
    {
        get
        {
#if UNITY_EDITOR || !UNITY_ANDROID
        return false;
#else
        AndroidJavaClass connectionClass = new AndroidJavaClass("rixment.awu.Main");
        AndroidJavaClass unity = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject currentActivity = unity.GetStatic<AndroidJavaObject>("currentActivity");

        return connectionClass.CallStatic<bool>("IsConnectedViaWifi", currentActivity);
#endif
        }
    }

    public static void ShareText(string title, string subject, string text)
    {
#if UNITY_EDITOR || !UNITY_ANDROID
        Debug.Log(string.Format("title: {0}\nsubject: {1}\ntext: {2}", title, subject, text));
#else
        AndroidJavaClass connectionClass = new AndroidJavaClass("rixment.awu.Main");
        AndroidJavaClass unity = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject currentActivity = unity.GetStatic<AndroidJavaObject>("currentActivity");

        connectionClass.CallStatic("shareText", currentActivity, title, subject, text);
#endif
    }
}
