package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.request.AppUpdateParam;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.base.BaseModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by CWQ on 2017/6/13.
 */

public class MainModelImpl extends BaseModel implements MainModel {

    public MainModelImpl(ApiService apiService) {
        super(apiService);
    }

    @Override
    public Observable<BaseBean<List<AppInfo>>> getAppUpdateInfoData(AppUpdateParam param) {
        return apiService.getAppUpdateInfoData(param.getPackageName(), param.getVersionCode());
    }
}
