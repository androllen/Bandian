package com.cc.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.cc.viewmodel.Listener.ViewModelListener;
import com.service.TimerService;

/**
 * Created by androllen on 2015/8/19.
 */
/*
* 用来接收事件和更新数据类
* 发起服务
* */
public abstract class BaseViewModel extends Handler {
    private static final String TAG = "BaseViewModel";
    private ViewModelListener mListener;
    private Context mContext;

    public BaseViewModel(Context context) {
        if (mContext == null) {
            mContext = context;
        }
    }

    public void startService() {
        Intent sIntent = new Intent(mContext, TimerService.class);
        sIntent.setAction("com.service.TimerService");
        mContext.startService(sIntent);
    }

    public void removeService() {
        Intent sIntent = new Intent(mContext, TimerService.class);
        mContext.stopService(sIntent);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (mListener != null) {
            mListener.onResponseMessage(msg);
        }
    }

    public void setListener(ViewModelListener listener) {
        if (listener != null) {
            mListener = listener;
        } else {
            mListener = null;
        }
    }

}