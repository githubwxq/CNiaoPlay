package com.chhd.cniaoplay.modle.base;

import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.http.CacheProvider;
import com.chhd.cniaoplay.http.GsonTSpeaker;
import com.chhd.per_library.util.UiUtils;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * Created by CWQ on 2017/5/28.
 */

public class BaseModel {

    protected ApiService apiService;
    protected CacheProvider provider;

    public BaseModel(ApiService apiService) {
        this.apiService = apiService;

        provider = new RxCache.Builder()
                .persistence(UiUtils.getContext().getFilesDir(), new GsonTSpeaker())
                .using(CacheProvider.class);
    }
}
