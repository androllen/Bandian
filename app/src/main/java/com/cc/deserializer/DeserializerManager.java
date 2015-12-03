package com.cc.deserializer;

import android.content.Context;

import com.cc.deserializer.Json.SearchLoveDeserializer;

/**
 * Created by androllen on 15/11/19.
 */
public class DeserializerManager {
    public DeserializerManager(){

    }

    private static DeserializerManager instance;

    public static DeserializerManager getInstance(){
        return instance;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new DeserializerManager();
        }
    }
    public BaseDeserializer BuildSearchLoveDeserializer()
    {
        return new SearchLoveDeserializer();
    }

}
