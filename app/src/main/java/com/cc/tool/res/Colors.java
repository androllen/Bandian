package com.cc.tool.res;

import android.graphics.Color;

/**
 * Created by androllen on 2015/8/19.
 */
public enum Colors {
    LIGHTGREY("#D3D3D3"), BLUE("#33B5E5"), PURPLE("#AA66CC"),
    GREEN("#99CC00"), ORANGE("#FFBB33"), RED("#FF4444");
    public String getCode(){
        return code;
    }
    private String code;
    private Colors(String code){
        this.code=code;
    }
    public int parseColor(){
        return Color.parseColor(code);
    }
}