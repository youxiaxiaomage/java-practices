package com.yxxmg.okttp;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description : http client请求工具类
 * @since : 2023/8/5
 */
@Slf4j
public final class HttpClientUtils {
    public static final MediaType APPLICATION_JSON = MediaType.parse("application/json");
    private static final String COOKIE = "Set-Cookie";

    public static String post(@NonNull String url, MediaType mediaType, @NonNull String requestParam) {
        log.info("post url:{}", url);
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().post(RequestBody.create(requestParam, mediaType)).url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            log.info("post response:{}", response);
            if (response.isSuccessful() && Objects.nonNull(response.body())) {
                return response.body().string();
            }
        } catch (

        Exception e) {
            log.error("post request error, with ex:", e);
            throw new OkhttpException(e.getMessage());
        }
        return null;
    }

    public static String postJson(@NonNull String url, @NonNull String requestParam) {
        return post(url, APPLICATION_JSON, requestParam);
    }

    public static String postForm(@NonNull String url, @NonNull Map<String, String> paramMap) {
        log.info("post url:{}", url);
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            // 构建FormBody,传入参数
            MultipartBody.Builder builder = new MultipartBody.Builder();
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
            MultipartBody multipartBody =
                builder.setType(Objects.requireNonNull(MediaType.parse("application/x-www-form-urlencoded"))).build();
            Request request = new Request.Builder().post(multipartBody).url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            log.info("post response:{}", response);
            if (response.isSuccessful() && Objects.nonNull(response.body())) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("post request error, with ex:", e);
            throw new OkhttpException(e.getMessage());
        }
        return null;
    }

    public static String put(String url, String requestParam) {
        log.info("put url:{}", url);
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                .put(RequestBody.create(requestParam, MediaType.parse("application/json"))).url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            log.info("put response:{}", response);
            if (response.isSuccessful() && Objects.nonNull(response.body())) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("put request error, with ex:", e);
            throw new OkhttpException(e.getMessage());
        }
        return null;
    }

    public static String get(String url) {
        log.info("get url:{}", url);
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request =
                new Request.Builder().addHeader("x-acs-btrip-so-corp-token", "so_corp_token").get().url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            log.info("get response:{}", response);
            return response.body().string();
        } catch (Exception e) {
            log.error("get request error, with ex:", e);
            throw new OkhttpException(e.getMessage());
        }
    }

    public static String get(String url, Map<String, String> headers) {
        log.info("get url:{}", url);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Request.Builder requestBuilder = new Request.Builder();
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Request request = requestBuilder.get().url(url).build();
        try (Response response = builder.connectTimeout(5, TimeUnit.MINUTES).build().newCall(request).execute()) {
            if (StringUtils.isNotBlank(headers.get(COOKIE))) {
                Map<String,
                    String> cookieHeader = Arrays.stream(headers.get(COOKIE).split(";"))
                        .map(h -> Pair.of(h.split("=")[0], h.split("=")[1]))
                        .collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v1));
                System.out.println(cookieHeader.get("satoken"));
            }
            log.info("get response:{}", response);
            if (response.isSuccessful() && Objects.nonNull(response.body())) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("get request error, with ex:", e);
            throw new OkhttpException(e.getMessage());
        }
        return null;
    }

    public static String delete(String url) {
        log.info("delete url:{}", url);
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().delete(RequestBody.create("", null)).url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            log.info("delete response:{}", response);
            if (response.isSuccessful() && Objects.nonNull(response.body())) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("delete request error, with ex:", e);
            throw new OkhttpException(e.getMessage());
        }
        return null;
    }
}
