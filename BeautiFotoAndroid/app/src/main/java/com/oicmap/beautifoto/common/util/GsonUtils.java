package com.oicmap.beautifoto.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by Eo on 10/4/2015.
 */
public class GsonUtils {

    static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        }
        return gson;
    }

    public static <T> String ObjectToString(T data) {

        return getGson().toJson(data);
    }

    public static <T> T StringToObject(String stringData, Class<T> clazz)  {
        return getGson().fromJson(stringData, clazz);
    }

    public static <T> T StringToArray(String strData,Type typeOfT){
        return getGson().fromJson(strData,typeOfT);
    }
}
