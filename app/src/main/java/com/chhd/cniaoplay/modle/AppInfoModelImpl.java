package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.base.BaseModel;

import rx.Observable;

/**
 * Created by CWQ on 2017/5/26.
 */

public class AppInfoModelImpl extends BaseModel implements AppInfoModel {

    public AppInfoModelImpl(ApiService apiService) {
        super(apiService);
    }

    @Override
    public Observable<BaseBean<PageBean<AppInfo>>> getRankData(int page) {
        return apiService.getRankData(page);
    }

    @Override
    public Observable<BaseBean<PageBean<AppInfo>>> getGameData(int page) {
        return apiService.getGameData(page);
    }

    @Override
    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppDataByCategory(int categoryId, int page) {
        return apiService.getFeaturedAppDataByCategory(categoryId, page);
    }

    @Override
    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppDataByCategory(int categoryId, int page) {
        return apiService.getTopListAppDataByCategory(categoryId, page);
    }

    @Override
    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppDataByCategory(int categoryId, int page) {
        return apiService.getNewListAppDataByCategory(categoryId, page);
    }
}
