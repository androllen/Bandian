package com.cc.view;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cc.bandian.R;
import com.cc.image.CubeImageView;
import com.cc.image.ImageLoader;
import com.cc.image.ImageLoaderFactory;
import com.cc.image.ImageTask;
import com.cc.image.impl.DefaultImageLoadHandler;
import com.cc.tool.manager.ListenerManager;
import com.cc.tool.manager.NetworkStatusManager;
import com.cc.viewmodel.Listener.MeListener;

public class MainActivity extends FragmentActivity implements MeListener{
    private final static String TAG = "MainActivity";
    private CubeImageView mCubeImageView;
    private String mUrl = "http://cube-sdk.liaohuqiu.net/assets/img/qrcode.png";

    @Override
    public void onChanged(boolean user_changed) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);

        mCubeImageView=(CubeImageView)findViewById(R.id.image_rounded_image_view1);
        DefaultImageLoadHandler defaultImageLoadHandler = new DefaultImageLoadHandler(getApplicationContext()) {
            @Override
            public void onLoadFinish(ImageTask imageTask, CubeImageView imageView, BitmapDrawable drawable) {
                super.onLoadFinish(imageTask, imageView, drawable);
                //loadRoundedImage();
            }
        };
        ImageLoader imageLoaderDefault = ImageLoaderFactory.create(getApplicationContext(), defaultImageLoadHandler);
        mCubeImageView.loadImage(imageLoaderDefault, mUrl);

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
