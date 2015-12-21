package com.cc.tool.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.cc.tool.help.SharedHelper;

/**
 * Created by androllen on 2015/8/27.
 */

public class DisplayUtils {
    public static int SCREEN_WIDTH_PIXELS;
    public static int SCREEN_HEIGHT_PIXELS;
    public static float SCREEN_DENSITY;
    public static float SCREEN_SCALEDDENSITY;
    public static int SCREEN_WIDTH_DP;
    public static int SCREEN_HEIGHT_DP;
    private static boolean sInitialed;

    public DisplayUtils() {
    }

    public static void init(Context context) {
        if(!sInitialed && context != null) {
            sInitialed = true;
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            SCREEN_WIDTH_PIXELS = dm.widthPixels;
            SCREEN_HEIGHT_PIXELS = dm.heightPixels;
            SCREEN_DENSITY = dm.density;
            SCREEN_SCALEDDENSITY=dm.scaledDensity;
            SCREEN_WIDTH_DP = (int)((float)SCREEN_WIDTH_PIXELS / dm.density);
            SCREEN_HEIGHT_DP = (int)((float)SCREEN_HEIGHT_PIXELS / dm.density);

            SharedHelper.getInstance().saveWindowMeta(SCREEN_DENSITY,SCREEN_WIDTH_DP,SCREEN_HEIGHT_DP);
        }
    }

    public static int dp2px(float dp) {
        //final float scale = mContext.getResources().getDisplayMetrics().density;
        float scale = SCREEN_DENSITY;
        return (int)(dp * scale + 0.5F);
    }

    public static int sp2px(float sp) {
        final float scale = SCREEN_SCALEDDENSITY;
        return (int) (sp * scale + 0.5f);
    }

    public static int designedDP2px(float designedDp) {
        if(SCREEN_WIDTH_DP != 320) {
            designedDp = designedDp * (float)SCREEN_WIDTH_DP / 320.0F;
        }

        return dp2px(designedDp);
    }

    public static void setPadding(View view, float left, float top, float right, float bottom) {
        view.setPadding(designedDP2px(left), dp2px(top), designedDP2px(right), dp2px(bottom));
    }
}
