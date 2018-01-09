package com.chhd.cniaoplay.modle.base;

import com.chhd.cniaoplay.http.ApiService;

/**
 * Created by CWQ on 2017/5/28.
 */

public class BaseModel {

    protected ApiService apiService;

    public BaseModel(ApiService apiService) {
        this.apiService = apiService;
    }
}
