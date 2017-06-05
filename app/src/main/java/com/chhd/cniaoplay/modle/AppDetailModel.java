package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.AppInfoDetail;
import com.chhd.cniaoplay.bean.BaseBean;

import rx.Observable;

/**
 * Created by CWQ on 2017/6/4.
 */

public interface AppDetailModel {

    Observable<BaseBean<AppInfoDetail>> getAppDetailData(int id);
}
