package com.djk;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dujinkai on 2018/7/11.
 * token上下文
 */
public class TokenContextHandler {

    public static ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<Map<String, String>>();

    /**
     * 设置token
     *
     * @param value token
     */
    public static void setToken(String value) {
        Map<String, String> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put("token", value);
    }


    /**
     * @return 获取token
     */
    public static String getToken() {
        Map<String, String> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map.get("token");
    }

    /**
     * 清理token
     */
    public static void remove() {
        threadLocal.remove();
    }
}
