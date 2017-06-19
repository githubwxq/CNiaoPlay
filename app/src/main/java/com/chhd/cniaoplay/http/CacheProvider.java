package com.chhd.cniaoplay.http;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.RecommendBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * Created by CWQ on 2017/6/18.
 */

public interface CacheProvider {

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<BaseBean<PageBean<AppInfo>>>> getCacheData(Observable<BaseBean<PageBean<AppInfo>>> observable);
}
