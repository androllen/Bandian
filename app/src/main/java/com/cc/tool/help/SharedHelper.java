package com.cc.tool.help;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.cc.model.Product;
import com.cc.tool.Const_def;

import java.io.File;

/**
 * Created by androllen on 2015/8/19.
 */
public class SharedHelper {

    private final static String TAG = "Settings";
    private final static String PREFS_NAME = "LovePrefsFile";
    //public int picQuality = Const_def.PIC_QUALITY_AUTO;
    private final static String KEY_F_STATIC_PKGNAME = "static_pkg_name";
    private final static String KEY_F_STATIC_APILEVEL = "static_api_level";
    private final static String KEY_F_STATIC_VERCODE = "static_ver_code";
    private final static String KEY_F_STATIC_VERNAME = "static_ver_name";
    private final static String KEY_F_STATIC_CHANNEL = "static_channel";
    private final static String KEY_F_STATIC_APP_PATH = "static_app_path";
    private final static String KEY_F_STATIC_CACHE_PATH = "static_cache_path";
    private final static String KEY_F_STATIC_CACHE_PATH_MYPIC = "static_cache_path_mypic";
    private static final String KEY_MARKETURL = "key_marketurl";
    private static final String KEY_ICONURL = "key_iconurl";

    private final static String KEY_INSTALL_APP_TIME = "install_app_time";

    //引导页
    public final static String KEY_APP_GUIDEACT="app_guide_act";

    // 原public static 转为 product meta
    private final static String KEY_F_STATIC_DENSITY = "static_density";
    private final static String KEY_F_STATIC_DISP_WIDTH = "static_disp_width";
    private final static String KEY_F_STATIC_DISP_HEIGHT = "static_disp_height";

    private static SharedPreferences mySharedPreferences;
    private static SharedHelper instance;
    private Context mContext;

    public static SharedHelper getInstance() {
        return instance;
    }

    public SharedHelper(Context context) {
        mContext = context;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new SharedHelper(context.getApplicationContext());
            mySharedPreferences = context.getSharedPreferences("filehelper", context.MODE_PRIVATE);
        }
    }

    public void saveVersionInfo() {
        File app_dir = mContext.getFilesDir();
        String appPath = Const_def.ROOTPATHDIR;
        if (app_dir != null && app_dir.exists()) {
            String path = app_dir.getAbsolutePath();
            if (!TextUtils.isEmpty(path)) {
                appPath = (path.endsWith("/")) ? path : path + "/";
            }
        }

        File cache_dir = mContext.getExternalCacheDir();
        String cachePath = appPath;
        if (cache_dir != null && cache_dir.exists()) {
            String path = cache_dir.getAbsolutePath();
            if (!TextUtils.isEmpty(path)) {
                cachePath = (path.endsWith("/")) ? path : path + "/";
            }
        }

        String cachePathMyPic = cachePath + "pic/";

        saveCashPaths(appPath, cachePath, cachePathMyPic);

        String dirStr = "sAppPath=" + appPath + "  sCachePath=" + cachePath
                + "  sCachePathMyPic=" + cachePathMyPic;


        String pkgName = null;
        String apiLevel = null;
        String verCode = null;
        String verName = null;
        String channel = null;

        try {
            String pn = mContext.getPackageName();
            ApplicationInfo ai = mContext.getPackageManager()
                    .getApplicationInfo(pn, PackageManager.GET_META_DATA);
            pkgName = ai.packageName;
            apiLevel = String.valueOf(ai.metaData.getInt("CLOUD_PROTOCOL_VERSION"));
            verCode = String.valueOf(mContext.getPackageManager().getPackageInfo(pn, 0).versionCode);
            verName = String.valueOf(mContext.getPackageManager().getPackageInfo(pn, 0).versionName);
            channel = ai.metaData.getString("UMENG_CHANNEL");
            if (TextUtils.isEmpty(channel)) {
                int channelInt = ai.metaData.getInt("UMENG_CHANNEL");
                if (channelInt != 0) {
                    channel = String.valueOf(channelInt);
                }
            }
        } catch (Exception e) {
        }

        if (TextUtils.isEmpty(channel)) {
            channel = "Unknown";
        }
        saveProductMeta(pkgName, apiLevel, verCode, verName,
                channel);
    }

    //保存
    public void SaveColorData(int id) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putInt("DEFAULT_COLOR", id);
        editor.commit();
    }

    public Boolean isData(int id) {
        Boolean b = false;
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        int ids = mySharedPreferences.getInt("timedefault", -1);
        if (ids > 0) {
            b = true;
        }
        return b;

    }


    // 持久化缓存相关的路径
    public void saveCashPaths(String appPath, String cachePath, String cachePathMyPic) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(KEY_F_STATIC_APP_PATH, appPath);
        editor.putString(KEY_F_STATIC_CACHE_PATH, cachePath);
        editor.putString(KEY_F_STATIC_CACHE_PATH_MYPIC, cachePathMyPic);
        // editor.putString(KEY_F_STATIC_EXT_PATH, extPath);

        editor.commit();
    }

    // 持久化产品meta
    public void saveProductMeta(String pkgName, String apiLevel, String verCode, String verName, String channel) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(KEY_F_STATIC_PKGNAME, pkgName);
        editor.putString(KEY_F_STATIC_APILEVEL, apiLevel);
        editor.putString(KEY_F_STATIC_VERCODE, verCode);
        editor.putString(KEY_F_STATIC_VERNAME, verName);
        editor.putString(KEY_F_STATIC_CHANNEL, channel);

        editor.commit();
    }

    // 读取产品meta
    public Product getProductMeta() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        Product product = new Product();
        product.pkgName = settings.getString(KEY_F_STATIC_PKGNAME, "");
        product.apiLevel = settings.getString(KEY_F_STATIC_APILEVEL, "");
        product.verCode = settings.getString(KEY_F_STATIC_VERCODE, "");
        product.verName = settings.getString(KEY_F_STATIC_VERNAME, "");
        product.channel = settings.getString(KEY_F_STATIC_CHANNEL, "");

        return product;
    }

    public void SaveNotifyData(int id) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putInt("timedefault", id);
        editor.commit();
    }

    public void saveGuideAct(){
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putBoolean(KEY_APP_GUIDEACT, false);
        editor.commit();
    }

    public boolean getGuideAct(){
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        boolean isGuide=mySharedPreferences.getBoolean(KEY_APP_GUIDEACT,true);
        return  isGuide;
    }

    public void ClearAllData() {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void removeData(String key) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void ReplaceData(String key, String value) {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.remove(key);
        editor.putString(key, value);
        editor.commit();
    }

    public int ExtraColorData() {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        int id = mySharedPreferences.getInt("DEFAULT_COLOR", -1);
        return id;
    }

    public int ExtraTimeData() {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        int id = mySharedPreferences.getInt("timedefault", -1);
        return id;
    }


    public String getVerName() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        return settings.getString(KEY_F_STATIC_VERNAME, "");
    }

    public String getPkgName() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        return settings.getString(KEY_F_STATIC_PKGNAME, "");
    }

    public String getAppPath() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        return settings.getString(KEY_F_STATIC_APP_PATH, "");
    }

    public String getCachePath() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        return settings.getString(KEY_F_STATIC_CACHE_PATH, "");
    }

    public void setIconUrl(String data) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_ICONURL, data);
        editor.commit();
    }

    public void setMarketUrl(String data) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_MARKETURL, data);
        editor.commit();
    }

    public void removeIcon() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(KEY_ICONURL);
        editor.commit();
    }

    public void clearAll() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    public String getIconUrl() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        return settings.getString(KEY_ICONURL, "");
    }

    public String getMarketUrl() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        return settings.getString(KEY_MARKETURL, "");
    }

    public boolean getBoolMarketUrl() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        return settings.getBoolean(KEY_MARKETURL, false);
    }

    public boolean getBoolIconUrl() {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, Application.MODE_PRIVATE);
        return settings.getBoolean(KEY_ICONURL, false);
    }
    // //持久化设备窗口meta
    public void saveWindowMeta(float density, int dispWidth, int dispHeight) {
        SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME,
                Application.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putFloat(KEY_F_STATIC_DENSITY, density);
        editor.putInt(KEY_F_STATIC_DISP_WIDTH, dispWidth);
        editor.putInt(KEY_F_STATIC_DISP_HEIGHT, dispHeight);

        editor.commit();
    }
    //读取数据
    public String ExtraData() {

        String name = mySharedPreferences.getString("name", "");

        return name;
    }
}
