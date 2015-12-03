package com.cc.cloud;

import android.content.Context;

import com.cc.tool.Const_def;
import com.cc.tool.manager.NetworkStatusManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONObject;

/**
 * Created by androllen on 15/11/27.
 */
public abstract class BaseService {
    private static final String TAG = "BaseService";

    private BaseRequest mBaseRequest;
    private BaseResponse mBaseResponse;
    private Context mContext;
    protected JSONObject mRequestProto;
    public BaseService(Context context) {
        mContext=context;
    }

    protected abstract void notifyResponse(BaseRequest request);
    protected abstract void notifyResponseError(BaseRequest request);

    public synchronized void doRequest(BaseRequest baseRequest) {
        mBaseRequest = baseRequest;
        mBaseResponse = baseRequest.getResponse();

        if (createProto() && NetworkStatusManager.getInstance().mState == NetworkStatusManager.State.UNKNOWN) {
            new TaskThread().start();
        } else {
            notifyResponseError(baseRequest);
        }
    }
    private class TaskThread extends Thread{
        @Override
        public void run() {
            super.run();
            //http 请求网络

            DefaultHttpClient httpClient = null;
            boolean hasError = false;

            try {

                HttpPost mHttpPost=new HttpPost(mBaseRequest.getUrl());
                mHttpPost.setHeader("Content-Type", "application/json");
                String protoStr = mBaseRequest.getRequestProto().toString();
                StringEntity mEntity=new StringEntity(protoStr, "");
                mHttpPost.setEntity(mEntity);
                httpClient=getHttpClient(mContext,"");

                HttpResponse resp=httpClient.execute(mHttpPost);
                mBaseResponse.mStatusCode=resp.getStatusLine().getStatusCode();

                mBaseResponse.setHttpResponse(resp);
            } catch (Exception e) {
                hasError = true;
            }finally{
                if (httpClient != null) {
                    httpClient.getConnectionManager().shutdown();
                }
            }

            if (mBaseResponse.mStatusCode == HttpStatus.SC_OK && !hasError) {
                notifyResponse(mBaseRequest);
            } else {
                notifyResponseError(mBaseRequest);
            }
        }

    }
    private boolean createProto() {
        boolean result = false;

        mRequestProto = new JSONObject();

        try {
            JSONObject jsonData = mBaseRequest.sendData();
            if (jsonData != null) {
                mRequestProto.put(Const_def.KEY_DATA, jsonData);
            }

            result = true;
            //MyLog.v(TAG, getCommand() + "------> mRequestProto=" + mRequestProto);
        } catch (Exception ex) {
            result = false;
            //MyLog.d(TAG, "createProto", ex);
        }

        return result;
    }

    private DefaultHttpClient getHttpClient(Context context, String userAgent) {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 10000);
        HttpConnectionParams.setSoTimeout(params, 30000);
        HttpProtocolParams.setContentCharset(params, Const_def.UTF8);
        ConnManagerParams.setTimeout(params, 10000L);
        DefaultHttpClient client = new DefaultHttpClient(params);
        return client;
    }
}
