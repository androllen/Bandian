package com.cc.view;

import android.app.Activity;
import android.os.Message;
import android.os.Bundle;

import com.cc.bandian.R;
import com.cc.tool.manager.NetworkStatusManager;
import com.cc.viewmodel.AboutViewModel;
import com.cc.viewmodel.Listener.ViewModelListener;

import in.srain.cube.util.CLog;

public class AboutActivity extends Activity implements ViewModelListener{
    private static final String TAG = "AboutActivity";

    private AboutViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_about);

        vm= new AboutViewModel(this);
        //注册一个viewmodel 更新UI数据
        vm.setListener(this);
        CLog.d("","");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始页面网络监听
        NetworkStatusManager.getInstance().startSendReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onResponseMessage(Message msg) {

    }
}
