package com.cc.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.cc.bandian.R;
import com.cc.tool.help.SharedHelper;
import com.cc.tool.util.DisplayUtils;
import com.cc.viewmodel.BootViewModel;
import com.cc.viewmodel.Listener.ViewModelListener;

import in.srain.cube.util.CLog;

public class BootActivity extends Activity implements ViewModelListener {

    private final static String TAG = "BootActivity";
    private BootViewModel vm;
    private ImageView mConverImg;

    private void showStatus(String status){
        String[] className =this.getClass().getName().split("\\.");
        Log.d("cube", String.format("%s %s",new Object[]{className[className.length-1],status}));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_boot);
        CLog.i(TAG, "onCreate");

        mConverImg = (ImageView) findViewById(R.id.img_conver);
        vm = new BootViewModel(this);
        vm.setListener(this);
        DisplayUtils.setPadding(mConverImg, 0, 0, 0, 0);

        vm.getId(1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        CLog.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        CLog.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        CLog.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        CLog.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CLog.i(TAG, "onDestroy");
    }

    private void launchBootAct() {
        if (SharedHelper.getInstance().getGuideAct()) {
            vm.getGuideAct(0);
        } else {
            Intent i = new Intent(this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Context c = this.getApplicationContext();
            c.startActivity(i);

            finish();
        }

    }

    private void launchGuideAct() {
        Intent i = new Intent(this, GuideActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Context c = this.getApplicationContext();
        c.startActivity(i);

        finish();
    }

    @Override
    public void onResponseMessage(Message msg) {
        switch (msg.what) {
            case 0:
                launchGuideAct();
                break;
            case 1:
                launchBootAct();
                break;
            default:
                break;
        }
    }
}
