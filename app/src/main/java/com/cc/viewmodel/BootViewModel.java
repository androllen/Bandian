package com.cc.viewmodel;

import android.content.Context;
import android.os.Message;

/**
 * Created by androllen on 2015/8/31.
 */
public class BootViewModel extends BaseViewModel {

    public BootViewModel(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }

    public void getId(int what) {
        Message message = this.obtainMessage(what);
        sendMessageDelayed(message, 3000);
    }

    public void getGuideAct(int what) {
        sendEmptyMessage(what);
    }
}
