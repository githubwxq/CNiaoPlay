package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.PageBean;

import io.reactivex.Observable;


/**
 * Created by CWQ on 2017/5/28.
 */

public interface AppInfoModel {

    Observable<BaseBean<PageBean<AppInfo>>> getRankData(int page);

    Observable<BaseBean<PageBean<AppInfo>>> getGameData(int page);

    Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppDataByCategory
            (int categoryId, int page);

    Observable<BaseBean<PageBean<AppInfo>>> getTopListAppDataByCategory
            (int categoryId, int page);

    Observable<BaseBean<PageBean<AppInfo>>> getNewListAppDataByCategory
            (int categoryId, int page);

}
