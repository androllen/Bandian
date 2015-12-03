package com.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by androllen on 2015/8/19.
 */
public class BaseService extends Service {

    private final static String TAG = "TimerService";

    // 当进入到系统根 注册定时服务
    public BaseService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        // mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        // TODO Auto-generated method stub
        super.onRebind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendMsg2Activity() {

        Intent intent = new Intent();
        intent.putExtra("count", 0);
        intent.setAction("com.service.TimerService");
        sendBroadcast(intent);
    }

    // private MyBinder myBinder = new MyBinder();
    // @Override
    // public int onStartCommand(Intent intent, int flags, int startId) {
    //
    // return super.onStartCommand(intent, flags, startId);
    //
    // // MyBinder binder = (MyBinder)service;
    // // BindService bindService = binder.getService();
    // // bindService.MyMethod();
    // }
    // public class MyBinder extends Binder{
    //
    // public TimerService getService(){
    // return TimerService.this;
    // }
    // }
}
