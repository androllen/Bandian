package com.cc.cloud.util;

import com.cc.cloud.BaseResponse;

import org.json.JSONObject;

public class FeedbackResp extends BaseResponse {

    private static final String TAG = "FeedbackResp";

    private JSONObject mDataProto;

    @Override
    protected void getDataProto(JSONObject json) {
        mDataProto=json;
    }



    @Override
    public String toString() {
        return TAG;
    }

}
