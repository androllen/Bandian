package com.cc.tool.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by androllen on 2015/8/27.
 */

public class NetworkStatusManager {
    public static final int NETWORK_CLASS_UNKNOWN = 0;
    public static final String NETWORK_CLASS_UNKNOWN_NAME = "UNKNOWN";
    public static final int NETWORK_CLASS_2G = 1;
    public static final String NETWORK_CLASS_2G_NAME = "2G";
    public static final int NETWORK_CLASS_3G = 2;
    public static final String NETWORK_CLASS_3G_NAME = "3G";
    public static final int NETWORK_CLASS_4G = 3;
    public static final String NETWORK_CLASS_4G_NAME = "4G";
    public static final int NETWORK_CLASS_WIFI = 999;
    public static final String NETWORK_CLASS_WIFI_NAME = "WIFI";
    private static final String TAG = "NetworkStatusManager";
    public static final String CONNECTIVITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final boolean DBG = true;
    private static NetworkStatusManager sInstance;
    private Context mContext;
    public NetworkStatusManager.State mState;
    private boolean mListening;
    private String mReason;
    private boolean mIsFailOver;
    private NetworkInfo mNetworkInfo;
    private boolean mIsWifi = false;
    private NetworkInfo mOtherNetworkInfo;
    private NetworkStatusManager.ConnectivityBroadcastReceiver mReceiver;

    private NetworkStatusManager() {
        this.mState = NetworkStatusManager.State.UNKNOWN;
        this.mReceiver = new NetworkStatusManager.ConnectivityBroadcastReceiver();
    }

    public static NetworkStatusManager getInstance() {
        return sInstance;
    }

    private static int getMobileNetworkClass(int networkType) {
        switch(networkType) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 1;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 2;
            case 13:
                return 3;
            default:
                return 0;
        }
    }
    public static void init(Context context) {
        sInstance = new NetworkStatusManager();
        sInstance.mIsWifi = checkIsWifi(context);
        sInstance.startListening(context);
    }

    //动态注册
    //如果关闭app则撤销广播通知
    public synchronized void startListening(Context context) {
        if(!this.mListening) {
            this.mContext = context;
            IntentFilter filter = new IntentFilter();
            filter.addAction(CONNECTIVITY_CHANGE_ACTION);
            context.registerReceiver(this.mReceiver, filter);
            this.mListening = true;
        }

    }

    public synchronized void startSendReceiver(){
        Intent intent=new Intent();
        intent.setAction(CONNECTIVITY_CHANGE_ACTION);
        mContext.sendBroadcast(intent);
    }

    private static boolean checkIsWifi(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null) {
            NetworkInfo networkInfo = connectivity.getNetworkInfo(1);
            if(networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                return true;
            }
        }

        return false;
    }

    public NetworkInfo getActiveNetwork(Context context){
        if (context == null)
            return null;
        ConnectivityManager mConnMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnMgr == null)
            return null;
        mNetworkInfo = mConnMgr.getActiveNetworkInfo(); // 获取活动网络连接信息
        return mNetworkInfo;
    }


    public int getNetworkType() {
        NetworkInfo activeNetworkInfo = this.getNetworkInfo();
        if(activeNetworkInfo != null) {
            if(activeNetworkInfo.getType() == 1) {
                return 999;
            }

            if(activeNetworkInfo.getType() == 0) {
                return getMobileNetworkClass(activeNetworkInfo.getSubtype());
            }
        }

        return 0;
    }

    public synchronized void stopListening() {
        if(this.mListening) {
            this.mContext.unregisterReceiver(this.mReceiver);
            this.mContext = null;
            this.mNetworkInfo = null;
            this.mOtherNetworkInfo = null;
            this.mIsFailOver = false;
            this.mReason = null;
            this.mListening = false;
        }

    }

    public NetworkInfo getNetworkInfo() {
        return this.mNetworkInfo;
    }

    public NetworkInfo getOtherNetworkInfo() {
        return this.mOtherNetworkInfo;
    }

    public boolean isFailover() {
        return this.mIsFailOver;
    }

    public String getReason() {
        return this.mReason;
    }

    public boolean isWifi() {
        return this.mIsWifi;
    }

    public String getNetworkTypeName() {
        switch(this.getNetworkType()) {
            case 0:
                return "UNKNOWN";
            case 1:
                return "2G";
            case 2:
                return "3G";
            case 3:
                return "4G";
            case 999:
                return "WIFI";
            default:
                return "UNKNOWN";
        }
    }

    private class ConnectivityBroadcastReceiver extends BroadcastReceiver {
        private ConnectivityBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(CONNECTIVITY_CHANGE_ACTION) && NetworkStatusManager.this.mListening) {
                boolean noConnectivity = intent.getBooleanExtra("noConnectivity", false);
                if(noConnectivity) {
                    NetworkStatusManager.this.mState = NetworkStatusManager.State.NOT_CONNECTED;
                } else {
                    NetworkStatusManager.this.mState = NetworkStatusManager.State.CONNECTED;
                }

                NetworkStatusManager.this.mNetworkInfo = intent.getParcelableExtra("networkInfo");
                NetworkStatusManager.this.mOtherNetworkInfo = intent.getParcelableExtra("otherNetwork");
                NetworkStatusManager.this.mReason = intent.getStringExtra("reason");
                NetworkStatusManager.this.mIsFailOver = intent.getBooleanExtra("isFailover", false);
                Log.d("NetworkStatusManager", "onReceive(): mNetworkInfo=" + NetworkStatusManager.this.mNetworkInfo + " mOtherNetworkInfo = " + (NetworkStatusManager.this.mOtherNetworkInfo == null ? "[none]" : NetworkStatusManager.this.mOtherNetworkInfo + " noConn=" + noConnectivity) + " mState=" + NetworkStatusManager.this.mState.toString());
                NetworkStatusManager.this.mIsWifi = NetworkStatusManager.checkIsWifi(NetworkStatusManager.this.mContext);
            } else {
                Log.w("NetworkStatusManager", "onReceived() called with " + NetworkStatusManager.this.mState.toString() + " and " + intent);
            }
        }
    }

    public enum State {
        UNKNOWN,
        CONNECTED,
        NOT_CONNECTED
    }
}
