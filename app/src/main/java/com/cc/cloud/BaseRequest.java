package com.cc.cloud;

/**
 * Created by androllen on 2015/8/19.
 */


import org.json.JSONObject;

public abstract class BaseRequest {

    protected JSONObject mRequestProto;

    public BaseRequest() {
    }

    protected abstract String getUrl();

    protected abstract String getCommand();

    protected abstract JSONObject sendData() throws Exception;

    public abstract BaseResponse getResponse();

    public JSONObject getRequestProto() {
        return mRequestProto;
    }

}
