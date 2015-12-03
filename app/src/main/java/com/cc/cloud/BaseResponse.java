package com.cc.cloud;

import com.cc.tool.Const_def;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by androllen on 2015/8/19.
 */

public abstract class BaseResponse {
    private static final String TAG = "BaseResponse";

    public static final int RESULT_SERVER_FAULT = 20;


    private static final String KEY_RESULT = "r";
    private static final String KEY_DATA = "d";

    public static final int Resoult_ok=10;
    public static final int Resoult_err=11;
    public static final int Resoult_null=12;

    protected int mStatusCode = -1;

    protected abstract void getDataProto(JSONObject json);

    private int mResultProto;
    public int getResultProto() {
        return mResultProto;
    }

    public void setHttpResponse(HttpResponse response) throws Exception {
        String entityStr = EntityUtils.toString(response.getEntity(), Const_def.UTF8);

        JSONObject mResponseProto = new JSONObject(entityStr);
        if (mResponseProto != null) {
            if (mResponseProto.has(KEY_RESULT)) {
                mResultProto = mResponseProto.getInt(KEY_RESULT);
                switch (mResultProto) {
                    case RESULT_SERVER_FAULT:
                        throw new Exception("Server error!");
                    default:
                        break;
                }
            }

            if (mResponseProto.has(KEY_DATA)) {
                String dataStr = mResponseProto.getString(KEY_DATA);
                JSONObject mDataProto = new JSONObject(dataStr);
                getDataProto(mDataProto);
            }

//            MyLog.v(TAG, "mResultProto=" + mResultProto);
//            MyLog.v(TAG, "mDataProto=" + mDataProto);
        }
    }
}
