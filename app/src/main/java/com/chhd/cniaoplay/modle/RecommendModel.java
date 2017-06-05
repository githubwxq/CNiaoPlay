package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.RecommendBean;

import rx.Observable;

/**
 * Created by CWQ on 2017/5/28.
 */

public interface RecommendModel {

    Observable<BaseBean<PageBean<AppInfo>>> getData();

    Observable<BaseBean<RecommendBean>> getRecommendData();
}
