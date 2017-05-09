package com.chhd.cniaoplay.data;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.Page;
import com.chhd.cniaoplay.http.Api;
import com.chhd.cniaoplay.http.HttpManager;

import retrofit2.Callback;

/**
 * Created by CWQ on 2017/5/9.
 */

public class RecommendModel {

    public void getData(Callback<Page<AppInfo>> callback) {
        HttpManager httpManager = new HttpManager();
        Api api = httpManager.getRetrofit(httpManager.getOkHttpClient()).create(Api.class);
        api.getApps("{'page':0}").enqueue(callback);
    }
}
