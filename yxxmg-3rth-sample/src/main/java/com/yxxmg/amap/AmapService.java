package com.yxxmg.amap;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.google.common.collect.Maps;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/11/23
 */
public class AmapService {
    public static String weatherInfo() throws IOException {
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("key", "afbfbc221f8b22a555296074b228914a");
        // all base
        paramMap.put("extensions", "all");
        // JSON XML
        paramMap.put("output", "JSON");
        paramMap.put("city", "320105");
        HttpUrl.Builder urlBuilder =
            HttpUrl.parse("https://restapi.amap.com/v3/weather/weatherInfo?parameters").newBuilder();
        if (MapUtils.isNotEmpty(paramMap)) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(),
                    URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name()));
            }
        }
        Request request = new Request.Builder().get().url(urlBuilder.build()).build();
        try (Response response = new OkHttpClient().newBuilder().build().newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
        }
        return null;
    }
}
