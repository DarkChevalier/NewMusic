package com.sunzhibin.newmusic.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Gson Tools
 * <p>
 * Gson 谷歌的JSON处理工具， 好处是不用担心缺失的属性. 如： 我们的映射对象有A、B、C三个属性,
 * 如果JSON字符串缺失了某一个，转换时候不会报错(这样就可以差别不大的映射对象共用), Jackson会报错。
 */
public class GsonUtils {
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * <pre>
     * JSON字符串转换为List数组, 提供两种方式(主要解决调用的容易程度)
     * 1. TypeToken<List<T>> USER_TOKEN 参数转换
     * 2. Class<T> cls 方式转换
     *
     * @param json
     * @return List<T>
     *
     * <pre>
     */
    public static <T> List<T> convertList(String json, TypeToken<List<T>> token) {
        try {

            if (TextUtils.isEmpty(json)) {
                return new ArrayList();
            }
            if (gson == null) {
                gson = new GsonBuilder().disableHtmlEscaping().create();

            }

            return gson.fromJson(json, token.getType());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * <pre>
     * Json格式转换, 由JSON字符串转化到制定类型T
     *
     * @param json
     * @param cls
     * @return T
     *
     * <pre>
     */
    public static <T> T parseObject(String json, Class<T> cls) {
        try {
            if (TextUtils.isEmpty(json)) {
                return null;
            }
            if (gson == null) {
                gson = new GsonBuilder().disableHtmlEscaping().create();

            }
            return gson.fromJson(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
            Loger.d("sunzhibin", e.getMessage());

        }
        return null;
    }

    /**
     * <pre>
     * java对象转化JSON
     *
     * @return String
     *
     * <pre>
     */
    public static String toJson(Object obj) {
        try {

            if (obj == null) {
                return "";
            }
            if (gson == null) {
                gson = new GsonBuilder().disableHtmlEscaping().create();

            }
            return gson.toJson(obj);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }


    /**
     * 使用Gson解析数据成List<Object>
     *
     * @param json 需要解析的Json数据
     * @param cls  类名
     * @return List<T>
     */
    public static <T> List<T> parseArray(String json, Class<T> cls) {

        if (TextUtils.isEmpty(json)) {
            return Collections.EMPTY_LIST;
        }

        List<T> list = new ArrayList<T>();
        try {
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (final JsonElement element : array) {
                list.add(new Gson().fromJson(element, cls));
            }
        } catch (Exception e) {
            Log.d("Gson", e.getMessage());
        }
        return list;
    }

    /**
     * 将json数据转成List<Map<String, Object>>
     *
     * @param json 需要解析的Json数据
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getListMaps(String json) {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Map<String, Object>>>() {
            }.getType();
            maps = gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }

    /**
     * 函数名称: parseData
     * 函数描述: 将json字符串转换为map
     *
     * @param data
     * @return
     */
    public static Map<String, Object> json2Map(String data) {
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, Object> map = g.fromJson(data, new TypeToken<Map<String, Object>>() {
        }.getType());
        return map;
    }


}
