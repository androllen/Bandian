package com.cc.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;

import com.cc.adapter.ListViewDataAdapter;
import com.cc.bandian.R;
import com.cc.cache.image.CubeImageView;
import com.cc.cache.image.ImageLoader;
import com.cc.cache.image.ImageLoaderFactory;
import com.cc.cache.image.impl.DefaultImageLoadHandler;
import com.cc.component.button.impl.UITabView;
import com.cc.component.holder.subholder.StringSmallImageViewHolder;
import com.cc.tool.help.Images;
import com.cc.tool.manager.NetworkStatusManager;
import com.cc.viewmodel.Listener.MeListener;

public class MainActivity extends FragmentActivity implements MeListener {
    private final static String TAG = "MainActivity";
    private CubeImageView mCubeImageView;
    private String mUrl = "http://cube-sdk.liaohuqiu.net/assets/img/qrcode.png";
    private ListView mListView;

    @Override
    public void onChanged(boolean user_changed) {

    }

    private UITabView mTabLayout_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);

//        ImageLoader loader = ImageLoaderFactory.create(this);
//        ((DefaultImageLoadHandler) loader.getImageLoadHandler()).setImageRounded(true, 25);
//
//
//        mListView = (ListView) findViewById(R.id.load_small_image_list_view);
//        ListViewDataAdapter<String> listadapter = new ListViewDataAdapter<String>();
//        listadapter.setViewHolderClass(this, StringSmallImageViewHolder.class, loader);
//        listadapter.getDataList().addAll(Images.getImages());
//        mListView.setAdapter(listadapter);
//        listadapter.notifyDataSetChanged();

        mTabLayout_1 = (UITabView)findViewById(R.id.tl_1);
        mTabLayout_1.loading();

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
