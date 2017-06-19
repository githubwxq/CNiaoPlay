package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.RecommendBean;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.base.BaseModel;

import io.reactivex.Observable;
import io.rx_cache2.Reply;

/**
 * Created by CWQ on 2017/5/9.
 */

public class RecommendModelImpl extends BaseModel implements RecommendModel {

    public RecommendModelImpl(ApiService apiService) {
        super(apiService);
    }

    @Override
    public Observable<BaseBean<PageBean<AppInfo>>> getData() {
        return apiService.getApps("{'page':0}");
    }

    @Override
    public Observable<Reply<BaseBean<PageBean<AppInfo>>>> getCacheData() {
        return provider.getCacheData(apiService.getApps("{'page':0}"));
    }

    @Override
    public Observable<BaseBean<RecommendBean>> getRecommendData() {
        return apiService.getRecommendData();
    }
}
