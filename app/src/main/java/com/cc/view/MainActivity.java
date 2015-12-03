package com.cc.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cc.bandian.R;
import com.cc.tool.manager.ListenerManager;
import com.cc.tool.manager.NetworkStatusManager;
import com.cc.viewmodel.Listener.MeListener;

public class MainActivity extends FragmentActivity implements MeListener{
    private final static String TAG = "MainActivity";
    Fragment fragment1;
    private FragmentManager fm;

    @Override
    public void onChanged(boolean user_changed) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
        fragment1 = new Fragment1();
        fm = getSupportFragmentManager();
        //初始化的时候需要显示一个fragment，假设我们显示第二个fragment
        //向容器中添加或者替换fragment时必须  开启事务  操作完成后   提交事务
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_main_container, fragment1).commit();
//        NetworkStatusManager.getInstance().startSendReceiver();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkStatusManager.getInstance().stopListening();
    }
}
