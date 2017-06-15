package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.AppInfoDetail;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.base.BaseModel;

import io.reactivex.Observable;


/**
 * Created by CWQ on 2017/6/4.
 */

public class AppDetailModelImpl extends BaseModel implements AppDetailModel {

    public AppDetailModelImpl(ApiService apiService) {
        super(apiService);
    }

    @Override
    public Observable<BaseBean<AppInfoDetail>> getAppDetailData(int id) {
        return apiService.getAppDetailData(id);
    }
}
