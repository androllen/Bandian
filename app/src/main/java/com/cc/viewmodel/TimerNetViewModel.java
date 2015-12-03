package com.cc.viewmodel;

import android.content.Context;
import android.os.Message;

/**
 * Created by androllen on 2015/8/19.
 */
public class TimerNetViewModel extends BaseViewModel {

    public TimerNetViewModel(Context context) {
        super(context);

    }


    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }

    public void sendEmptyMessageDelayed(int what) {
        this.sendEmptyMessageDelayed(what, 1000);
    }

    public void RemoveMsg(int what){
        this.removeMessages(what);
    }

}

