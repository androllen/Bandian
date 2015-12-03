package com.service;

import android.content.Intent;
import android.os.IBinder;
import android.os.Message;

import com.cc.viewmodel.Listener.ViewModelListener;
import com.cc.viewmodel.TimerNetViewModel;

/**
 * Created by androllen on 2015/8/19.
 */
public class TimerService extends BaseService implements ViewModelListener {
    private final static String TAG = "TimerService";
    private TimerNetViewModel timer;
    public static TimerNetListener mNetListener;

    // 当进入到系统根 注册定时服务
    public TimerService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer = new TimerNetViewModel(this);
        timer.setListener(this);
        timer.sendEmptyMessageDelayed(1);
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onResponseMessage(Message msg) {
        switch (msg.what) {
            case 0:
                break;
            case 1:
                if (mNetListener != null) {
                    mNetListener.onTimerNet();
                }
                timer.sendEmptyMessageDelayed(1);
                break;
            default:
                break;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        timer.RemoveMsg(1);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public interface TimerNetListener {
        public abstract void onTimerNet();
    }

}
