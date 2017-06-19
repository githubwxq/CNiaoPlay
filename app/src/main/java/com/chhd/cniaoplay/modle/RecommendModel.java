package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.RecommendBean;

import io.reactivex.Observable;
import io.rx_cache2.Reply;

/**
 * Created by CWQ on 2017/5/28.
 */

public interface RecommendModel {

    Observable<BaseBean<PageBean<AppInfo>>> getData();

    Observable<Reply<BaseBean<PageBean<AppInfo>>>> getCacheData();

    Observable<BaseBean<RecommendBean>> getRecommendData();
}
