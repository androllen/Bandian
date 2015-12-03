package com.cc.viewmodel.Listener;

import com.cc.cloud.BaseRequest;

/**
 * Created by androllen on 15/11/27.
 */
public interface ServiceListener {
    void onResponse(BaseRequest request);

    void onResponseError(BaseRequest request);
}
