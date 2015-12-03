package com.cc.cloud.util;

import android.content.Context;

import com.cc.cloud.BaseRequest;
import com.cc.cloud.BaseResponse;
import com.cc.tool.Const_def;

import org.json.JSONObject;

public class FeedbackReq extends BaseRequest {

    private static final String TAG = "FeebackReq";
    private static final String KEY_CONTENT = "fb";
    private static final String KEY_MEMBERSHIP = "ms";

    private String mContent;
    private Context mContext;

    private FeedbackResp mResponse;

    public FeedbackReq(Context context) {
        mContext = context;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getContent() {
        return mContent;
    }

    @Override
    protected String getUrl() {
        return Const_def.MSG_URL;
    }

    @Override
    protected String getCommand() {
        return "60";
    }

    @Override
    protected JSONObject sendData() throws Exception {
        JSONObject data = new JSONObject();
        if (mContent != null) {
            data.put(KEY_CONTENT, mContent);
        }

//        data.put(KEY_MEMBERSHIP, Me.sMembership);
//
//        String val = DeviceUtil.getFp(mContext);
//        MyLog.d(TAG, "===　val " + val);
//        if (!TextUtils.isEmpty(val)) {
//            data.put(DeviceUtil.KEY_FINGERPRINT, val);
//            val = null;
//        }
//
//        val = DeviceUtil.getPhoneNumber(mContext);
//        if (!TextUtils.isEmpty(val)) {
//            data.put(DeviceUtil.KEY_MOBILE, val);
//            val = null;
//        }
//
//        val = DeviceUtil.getIMEI(mContext);
//        if (!TextUtils.isEmpty(val)) {
//            data.put(DeviceUtil.KEY_IMEI, val);
//            val = null;
//        }
//
//        val = DeviceUtil.getIMSI(mContext);
//        if (!TextUtils.isEmpty(val)) {
//            data.put(DeviceUtil.KEY_IMSI, val);
//            val = null;
//        }
//

        return data;
    }

    @Override
    public BaseResponse getResponse() {

        if (mResponse == null) {
            mResponse = new FeedbackResp();
        }
        return mResponse;
    }

    @Override
    public String toString() {
        return TAG;
    }


}
