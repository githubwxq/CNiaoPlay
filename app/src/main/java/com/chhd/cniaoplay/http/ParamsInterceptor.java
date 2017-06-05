package com.chhd.cniaoplay.http;

import android.text.TextUtils;

import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.util.LoggerUtils;
import com.chhd.per_library.util.DensityUtil;
import com.chhd.per_library.util.DeviceUtils;
import com.chhd.per_library.util.SpUtils;
import com.chhd.per_library.util.UiUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by CWQ on 2017/5/20.
 */

public class ParamsInterceptor implements Interceptor {

    private final MediaType contentType = MediaType.parse("application/json; charset=utf-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HashMap<String, Object> rootMap = new HashMap<>();

        HashMap<String, Object> commomParamsMap = new HashMap<>();
        commomParamsMap.put("imei", DeviceUtils.getIMEI());
        commomParamsMap.put("model", DeviceUtils.getModel());
        commomParamsMap.put("la", DeviceUtils.getLanguage());
        commomParamsMap.put("os", DeviceUtils.getBuildVersionIncremental());
        commomParamsMap.put("resolution", DensityUtil.getScreenW() + "*" + DensityUtil.getScreenH());
        commomParamsMap.put("sdk", DeviceUtils.getBuildVersionSDK() + "");
        commomParamsMap.put("densityScaleFactor", UiUtils.getResources().getDisplayMetrics().density + "");
        commomParamsMap.put("token", App.token);

        if (request.method().equals("GET")) {
            HttpUrl httpUrl = request.url();
            Set<String> keys = httpUrl.queryParameterNames();
            for (String key : keys) {
                if ("p".equals(key)) {
                    String json = httpUrl.queryParameter("p");
                    if (!TextUtils.isEmpty(json)) {
                        HashMap<String, Objects> map = new Gson().fromJson(json, HashMap.class);
                        for (Map.Entry<String, Objects> entry : map.entrySet()) {
                            rootMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                } else {
                    rootMap.put(key, httpUrl.queryParameter(key));
                }
            }
            rootMap.put("publicParams", commomParamsMap);
            String newJsonParams = new Gson().toJson(rootMap);
            String url = httpUrl.scheme() + "://" + httpUrl.host() + ":" + httpUrl.port() + httpUrl.encodedPath();
            //112.124.22.238/course_api/cniaoplay/featured2?p={"publicParams":{"resolution":"720*1184","sdk":"22","la":"en","densityScaleFactor":"2.0","os":"3728910","model":"Android SDK built for x86"},"page":0.0}
            url = url + "?" + "p" + "=" + newJsonParams;
            request = request.newBuilder().url(url).build();
        } else if (request.method().equals("POST")) {
            RequestBody requestBody = request.body();
            if (requestBody instanceof FormBody) {
                FormBody body = (FormBody) requestBody;
                FormBody.Builder builder = new FormBody.Builder();
                for (int i = 0; i < body.size(); i++) {
                    builder.add(body.encodedName(i), body.encodedValue(i));
                }
                body = builder.build();
                request.newBuilder().post(body).build();
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                String oldJsonParams = buffer.readUtf8();
                rootMap = new Gson().fromJson(oldJsonParams, HashMap.class);
                rootMap.put("publicParams", commomParamsMap);
                String newJsonParams = new Gson().toJson(rootMap);
                request = request.newBuilder().post(RequestBody.create(contentType, newJsonParams)).build();
            }
        }
        return chain.proceed(request);
    }
}
