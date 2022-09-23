package com.atguigu.flume.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xj
 * @create 2022-03-04 19:46
 **/
public class TimeStampInterceptor implements Interceptor {
    ArrayList<Event> events = new ArrayList<>();
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 取出body中的时间戳，它再"ts"字段保存。
        String log = new String(event.getBody(), StandardCharsets.UTF_8);
        JSONObject jsonObject = JSONObject.parseObject(log);
        String ts = jsonObject.getString("ts");
        // 为header增加时间戳
        Map<String, String> headers = event.getHeaders();
        headers.put("timestamp",ts);
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        events.clear();
        for (Event event : list) {
            events.add(intercept(event));
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {
        @Override
        public Interceptor build() {
            return new TimeStampInterceptor();
        }

        @Override
        public void configure(Context context) {
        }
    }
}
