package com.cc.cloud.service;

import android.content.Context;

import com.cc.cloud.BaseRequest;
import com.cc.cloud.BaseService;
import com.cc.cloud.util.FeedbackReq;
import com.cc.viewmodel.Listener.ServiceListener;

/**
 * Created by androllen on 15/11/27.
 */
public class CCUniverseService extends BaseService {
    private static final String TAG = "CCUniverseService";

    private FeedbackReq req;
    private ServiceListener mListener;
    private Context mContext;
    public CCUniverseService(Context context) {
        super(context);
        mContext=context;
        req = new FeedbackReq(context);
    }

    public void sendAbout() {
        doRequest(req);
    }

    public void setListener(ServiceListener listener)  {
        mListener = listener;
    }

    @Override
    protected void notifyResponse(BaseRequest request) {
        if(request!=null){
            if (mListener != null) {
                mListener.onResponse(request);
            }

        }
    }

    @Override
    protected void notifyResponseError(BaseRequest request) {
//        boolean isBaseAct = mContext instanceof BaseAct;
//        if (Const_def.sCloudMaintaining && isBaseAct) {
//            BaseAct ba = (BaseAct) mContext;
//            ba.showCloudMaintainingDlg();
//        }
        if(request!=null){
            if (mListener != null) {
                mListener.onResponseError(request);
            }

        }
    }
}
