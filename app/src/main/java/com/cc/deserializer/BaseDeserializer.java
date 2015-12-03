package com.cc.deserializer;

import com.cc.model.BaseModel;
import java.util.List;

/**
 * Created by androllen on 15/11/19.
 */
public abstract class BaseDeserializer {
    /// <summary>
    /// 反序列化单个对象.
    /// </summary>
    /// <param name="content">需要反序列化成单个对象的字符串.</param>
    /// <returns>返回对象.</returns>
    public abstract BaseModel Read(String content);

    /// <summary>
    /// 反序列化对象列表.
    /// </summary>
    /// <param name="content">需要反序列化成列表的字符串.</param>
    /// <returns>返回对象列表</returns>
    public abstract List ReadList(String content);

    public abstract int ReadIsSuccess(String content);
}
