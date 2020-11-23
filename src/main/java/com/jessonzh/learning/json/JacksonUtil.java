package com.jessonzh.learning.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vip.vjtools.vjkit.mapper.JsonMapper;

public class JacksonUtil {

    public static <T> String toJson(T obj) {
        return JsonMapper.INSTANCE.toJson(obj);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        return JsonMapper.INSTANCE.fromJson(json, clazz);
    }
}
