package com.cc.viewmodel;

import android.content.Context;
import android.os.Message;

/**
 * Created by androllen on 2015/8/19.
 */
public class InitViewModel extends BaseViewModel {
    private static final String TAG = "InitViewModel";

    public InitViewModel(Context context) {
        super(context);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }

    public void getId(int what) {
        this.sendEmptyMessage(what);
    }
}
