package com.cc.bandian;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Environment;
import android.os.Message;

import com.baidu.mapapi.SDKInitializer;
import com.cc.cache.CacheManager;
import com.cc.cache.CacheManagerFactory;
import com.cc.cache.image.ImageLoaderFactory;
import com.cc.cache.image.impl.DefaultImageLoadHandler;
import com.cc.cache.image.impl.DefaultImageReSizer;
import com.cc.db.DbHelper;
import com.cc.deserializer.DeserializerManager;

import com.cc.tool.CCDebug;
import com.cc.tool.help.SharedHelper;
import com.cc.tool.manager.NetworkStatusManager;

import com.cc.tool.util.DisplayUtils;
import com.cc.viewmodel.InitViewModel;
import com.cc.viewmodel.Listener.ViewModelListener;

import java.io.File;

/**
 * Created by androllen on 2015/8/19.
 */
public class MainPage extends Application implements ViewModelListener {

    private InitViewModel vm;
    private static MainPage instance=null;
    public static MainPage getInstance(){
        if(instance==null){
            instance=new MainPage();
        }
        return instance;
    }
	@Override
	public Context createConfigurationContext(
			Configuration overrideConfiguration) {

		//Configuration config = getResources().getConfiguration();

		return super.createConfigurationContext(overrideConfiguration);
	}

    @Override
    public void onCreate() {
        super.onCreate();

        //调试
        CCDebug.DEBUG_IMAGE=true;

        //数据缓存
        SharedHelper.init(this);
        //版本信息
        SharedHelper.getInstance().saveVersionInfo();
        //图片缓存
        CacheManager.init(getApplicationContext());


        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(this);

        //数据库初始化
        DbHelper.init(this);
        //自适应初始化
        DisplayUtils.init(this);

        SharedHelper.getInstance().saveWindowMeta(DisplayUtils.SCREEN_DENSITY, DisplayUtils.SCREEN_WIDTH_DP, DisplayUtils.SCREEN_HEIGHT_DP);

        //网络有效性
        NetworkStatusManager.init(this);


        //序列化
        DeserializerManager.init(this);

        vm = new InitViewModel(this);
        vm.setListener(this);
        vm.getId(0);

        CacheManagerFactory.initDefaultCache(this, null, -1, -1);
        initImageLoader();
    }
    private void initImageLoader() {

        File path1 = Environment.getExternalStoragePublicDirectory("cube/test1/a/b/c");
        ImageLoaderFactory.customizeCache(
                this,
                // memory size
                1024 * 10,
                // disk cache directory
                path1.getAbsolutePath(),
                //null,
                // disk cache size
                ImageLoaderFactory.DEFAULT_FILE_CACHE_SIZE_IN_KB
        );

        DefaultImageLoadHandler handler = new DefaultImageLoadHandler(this);
        // handler.setLoadingImageColor("#999999");

        ImageLoaderFactory.setDefaultImageLoadHandler(handler);
        ImageLoaderFactory.setDefaultImageReSizer(DefaultImageReSizer.getInstance());
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    @Override
    public void onResponseMessage(Message msg) {
        switch (msg.what) {
            case 0:
//                Intent sIntent=new Intent(MainPage.this,BootActivity.class);
//                startActivity(sIntent);
                //		CCInfoService ccInfoService=new CCInfoService(this);
                //		ccInfoService.setListener(new BaseRequest.ResponseListener() {
                //
                //			@Override
                //			public void onResponse() {
                //
                //			}spile
                //		});
                //		ccInfoService.doRequest();
                break;
            case 1:
//				Intent sIntent=new Intent(this,TimerService.class);
//				sIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				startService(sIntent);
                break;
            default:
                break;
        }

    }

}

