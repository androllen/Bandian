package com.cc.cache;


import com.cc.cloud.util.JsonData;

public abstract class QueryJsonHandler implements QueryHandler<JsonData> {

    @Override
    public JsonData processRawDataFromCache(JsonData rawData) {
        return rawData;
    }
}
