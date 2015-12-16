package com.cc.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.cc.bandian.R;
import com.cc.cloud.BaseRequest;
import com.cc.cloud.service.CCUniverseService;
import com.cc.cloud.util.FeedbackResp;
import com.cc.view.AboutActivity;
import com.cc.viewmodel.Listener.ServiceListener;

/**
 * Created by androllen on 15/11/27.
 */
public class AboutViewModel extends BaseViewModel implements View.OnClickListener, ServiceListener {
    private static final String TAG = "AboutViewModel";

    private CCUniverseService mService;
    private AboutActivity mActivity;
    private Context mContext;
    private Button mButton;

    public AboutViewModel(Context context) {
        super(context);
        this.mContext = context;
        this.mActivity = (AboutActivity) context;
        this.mButton = (Button) mActivity.findViewById(R.id.btn_show);
        this.mButton.setOnClickListener(this);

        //注册一个服务用来请求数据 接收数据
        mService = new CCUniverseService(mContext);
        //ServiceListener 观察服务有消息进来通知 AboutViewModel onResponse
        mService.setListener(this);

    }

    @Override
    public void onResponse(BaseRequest request) {
        FeedbackResp resp = (FeedbackResp) request.getResponse();
        int proto = resp.getResultProto();

    }

    @Override
    public void onResponseError(BaseRequest request) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show) {
            mService.sendAbout();
        }
    }


    public void getId(int what) {
        this.sendEmptyMessage(what);
    }
}
