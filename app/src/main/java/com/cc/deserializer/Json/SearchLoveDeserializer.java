package com.cc.deserializer.Json;

import com.cc.deserializer.BaseDeserializer;
import com.cc.model.BaseModel;

import java.util.List;

/**
 * Created by androllen on 15/11/23.
 */
public class SearchLoveDeserializer extends BaseDeserializer{
    public SearchLoveDeserializer() {
    }

    @Override
    public BaseModel Read(String content) {
        return null;
    }

    @Override
    public int ReadIsSuccess(String content) {
        return 10;
    }

    @Override
    public List ReadList(String content) {
        return null;
    }

//    BaseDeserializer serializer = DeserializerManager.getInstance().BuildSearchLoveDeserializer();
//    int temp=serializer.ReadIsSuccess("");
//    switch (temp){
//        case 10:
//            break;
//        default:
//            break;
//    }
}
