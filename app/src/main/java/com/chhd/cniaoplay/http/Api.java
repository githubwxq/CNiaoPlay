package com.chhd.cniaoplay.http;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.Page;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by CWQ on 2017/5/6.
 */

public interface Api {

    String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";

    @GET("featured")
    Call<Page<AppInfo>> getApps(@Query("p") String jsonParam);
}
