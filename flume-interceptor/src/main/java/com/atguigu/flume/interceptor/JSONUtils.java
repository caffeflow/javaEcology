package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

/**
 * @author xj
 * @create 2022-03-04 17:00
 **/
public class JSONUtils {
    public static boolean isJSONValidate(String log){
        try {
            JSON.isValid(log);
            return true;
        }catch (JSONException e){
            return false;
        }
    }
}
