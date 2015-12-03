package com.cc.model.Util;

import com.cc.model.BaseModel;

/**
 * Created by androllen on 15/11/27.
 */
public class AliPay extends BaseModel {
    public AliPay(){

    }
    public AliPay(String url) {
        Url = url;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String Url;
}
