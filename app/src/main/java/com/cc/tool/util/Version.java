package com.cc.tool.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;

/**
 * Created by androllen on 2015/8/27.
 */

public class Version {
    private Version() {
    }

    @TargetApi(11)
    public static void enableStrictMode() {
        if(hasGingerbread()) {
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder = (new StrictMode.ThreadPolicy.Builder()).detectAll().penaltyLog();
            android.os.StrictMode.VmPolicy.Builder vmPolicyBuilder = (new android.os.StrictMode.VmPolicy.Builder()).detectAll().penaltyLog();
            if(hasHoneycomb()) {
                threadPolicyBuilder.penaltyFlashScreen();
            }

            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }

    }

    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= 8;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= 9;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= 12;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= 19;
    }
}
