package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.request.AppUpdateParam;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by CWQ on 2017/6/13.
 */

public interface MainModel {

    Observable<BaseBean<List<AppInfo>>> getAppUpdateInfoData(AppUpdateParam param);
}
