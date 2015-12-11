package com.cc.view;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mapapi.map.MapView;
import com.cc.bandian.R;

import in.srain.cube.util.CLog;

public class MapActivity extends Activity {
    private final static String TAG = "MapActivity";

    private MapView mMapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_map);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mMapView.showZoomControls(false);
        mMapView.showScaleControl(false);
    }


    @Override
    protected void onStart() {
        super.onStart();
        CLog.i(TAG, "onStart");
    }

    //打开独占程序 打开动画
    @Override
    protected void onResume() {
        super.onResume();
        CLog.i(TAG, "onResume");
        mMapView.onResume();
    }

    //关闭相机 动画等
    @Override
    protected void onPause() {
        super.onPause();
        CLog.i(TAG, "onPause");
        mMapView.onPause();
    }

    //等到新页跑这个onResume调用
    @Override
    protected void onStop() {
        super.onStop();
        CLog.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CLog.i(TAG, "onDestroy");
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
}
