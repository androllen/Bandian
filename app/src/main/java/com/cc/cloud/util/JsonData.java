package com.cc.cloud.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class JsonData
{
  private static final String EMPTY_STRING = "";
  private static final JSONArray EMPTY_JSON_ARRAY = new JSONArray();
  private static final JSONObject EMPTY_JSON_OBJECT = new JSONObject();
  private Object mJson;

  public static JsonData newMap()
  {
    return create(new HashMap());
  }

  public static JsonData newList() {
    return create(new ArrayList());
  }

  public static JsonData create(String str) {
    Object object = null;
    if ((str != null) && (str.length() >= 0))
      try {
        JSONTokener jsonTokener = new JSONTokener(str);
        object = jsonTokener.nextValue();
      }
      catch (Exception e) {
      }
    return create(object);
  }

  public static JsonData create(Object o) {
    JsonData json = new JsonData();
    if ((o instanceof JSONArray) || (o instanceof JSONObject))
      json.mJson = o;

    if (o instanceof Map)
      json.mJson = new JSONObject((Map)o);

    if (o instanceof Collection)
      json.mJson = new JSONArray((Collection)o);

    return json;
  }

  public Object getRawData() {
    return this.mJson;
  }

  public JsonData optJson(String name)
  {
    Object ret = null;
    if (this.mJson instanceof JSONObject)
      ret = ((JSONObject)this.mJson).opt(name);

    return create(ret);
  }

  public JsonData optJson(int index)
  {
    Object ret = null;
    if (this.mJson instanceof JSONArray)
      ret = ((JSONArray)this.mJson).opt(index);

    return create(ret);
  }

  public String optString(String name) {
    return optMapOrNew().optString(name);
  }

  public String optString(String name, String fallback) {
    return optMapOrNew().optString(name, fallback);
  }

  public String optString(int index) {
    return optArrayOrNew().optString(index);
  }

  public String optString(int index, String fallback) {
    return optArrayOrNew().optString(index, fallback);
  }

  public int optInt(String name) {
    return optMapOrNew().optInt(name);
  }

  public int optInt(String name, int fallback) {
    return optMapOrNew().optInt(name, fallback);
  }

  public int optInt(int index) {
    return optArrayOrNew().optInt(index);
  }

  public int optInt(int index, int fallback) {
    return optArrayOrNew().optInt(index, fallback);
  }

  public boolean optBoolean(String name) {
    return optMapOrNew().optBoolean(name);
  }

  public boolean optBoolean(String name, boolean fallback) {
    return optMapOrNew().optBoolean(name, fallback);
  }

  public boolean optBoolean(int index) {
    return optArrayOrNew().optBoolean(index);
  }

  public boolean optBoolean(int index, boolean fallback) {
    return optArrayOrNew().optBoolean(index, fallback);
  }

  public double optDouble(String name) {
    return optMapOrNew().optDouble(name);
  }

  public double optDouble(String name, double fallback) {
    return optMapOrNew().optDouble(name, fallback);
  }

  public double optDouble(int index) {
    return optArrayOrNew().optDouble(index);
  }

  public double optDouble(int index, double fallback) {
    return optArrayOrNew().optDouble(index, fallback);
  }

  public boolean has(String name) {
    return optMapOrNew().has(name);
  }

  public boolean has(int index) {
    return (optArrayOrNew().length() > index);
  }

  public JSONObject optMapOrNew() {
    if (this.mJson instanceof JSONObject)
      return ((JSONObject)this.mJson);

    return EMPTY_JSON_OBJECT;
  }

  private Object valueForPut(Object value) {
    if (value instanceof JsonData)
      return ((JsonData)value).getRawData();

    return value;
  }

  public void put(String key, Object value)
  {
    if (this.mJson instanceof JSONObject)
      try {
        ((JSONObject)this.mJson).put(key, valueForPut(value));
      } catch (JSONException e) {
        e.printStackTrace();
      }
  }

  public void put(Object value)
  {
    if (this.mJson instanceof JSONArray)
      ((JSONArray)this.mJson).put(valueForPut(value));
  }

  public void put(int index, Object value)
  {
    if (this.mJson instanceof JSONArray)
      try {
        ((JSONArray)this.mJson).put(index, valueForPut(value));
      } catch (JSONException e) {
        e.printStackTrace();
      }
  }

  public JsonData editMap(int index)
  {
    if (has(index))
      return optJson(index);

    JsonData map = newMap();
    put(index, map);
    return map;
  }

  public JsonData editMap() {
    JsonData map = newMap();
    put(map);
    return map;
  }

  public JsonData editMap(String key) {
    if (has(key))
      return optJson(key);

    JsonData map = newMap();
    put(key, map);
    return map;
  }

  public JsonData editList(String key) {
    if (has(key))
      return optJson(key);

    JsonData list = newList();
    put(key, list);
    return list;
  }

  public JsonData editList(int index) {
    if (has(index))
      return optJson(index);

    JsonData list = newList();
    put(index, list);
    return list;
  }

  public JsonData editList() {
    JsonData list = newList();
    put(list);
    return list;
  }

  public JSONArray optArrayOrNew() {
    if (this.mJson instanceof JSONArray)
      return ((JSONArray)this.mJson);

    return EMPTY_JSON_ARRAY;
  }

  public int length() {
    if (this.mJson instanceof JSONArray)
      return ((JSONArray)this.mJson).length();

    if (this.mJson instanceof JSONObject)
      return ((JSONObject)this.mJson).length();

    return 0;
  }

  public Iterator<String> keys()
  {
    return optMapOrNew().keys();
  }

  public String toString() {
    if (this.mJson instanceof JSONArray)
      return ((JSONArray)this.mJson).toString();
    if (this.mJson instanceof JSONObject)
      return ((JSONObject)this.mJson).toString();

    return "";
  }

  public ArrayList<JsonData> toArrayList() {
    ArrayList arrayList = new ArrayList();
    if (this.mJson instanceof JSONArray) {
      JSONArray array = (JSONArray)this.mJson;
      for (int i = 0; i < array.length(); ++i)
        arrayList.add(i, create(array.opt(i)));
    }
    else if (this.mJson instanceof JSONObject) {
      JSONObject json = (JSONObject)this.mJson;

      Iterator it = json.keys();

      while (it.hasNext()) {
        String key = (String)it.next();
        arrayList.add(create(json.opt(key)));
      }
    }
    return arrayList;
  }

  public <T> ArrayList<T> asList(JsonConverter<T> converter) {
    ArrayList arrayList = new ArrayList();
    if (this.mJson instanceof JSONArray) {
      JSONArray array = (JSONArray)this.mJson;
      for (int i = 0; i < array.length(); ++i)
        arrayList.add(converter.convert(create(array.opt(i))));
    }
    else if (this.mJson instanceof JSONObject) {
      JSONObject json = (JSONObject)this.mJson;

      Iterator it = json.keys();
      while (it.hasNext()) {
        String key = (String)it.next();
        arrayList.add(converter.convert(create(json.opt(key))));
      }
    }
    return arrayList;
  }

  public <T> ArrayList<T> asList() {
    ArrayList arrayList = new ArrayList();
    if (this.mJson instanceof JSONArray) {
      JSONArray array = (JSONArray)this.mJson;
      for (int i = 0; i < array.length(); ++i)
        arrayList.add(array.opt(i));
    }
    else if (this.mJson instanceof JSONObject) {
      JSONObject json = (JSONObject)this.mJson;

      Iterator it = json.keys();
      while (it.hasNext()) {
        String key = (String)it.next();
        arrayList.add(json.opt(key));
      }
    }
    return arrayList;
  }

  public static abstract interface JsonConverter<T>
  {
    public abstract T convert(JsonData paramJsonData);
  }
}