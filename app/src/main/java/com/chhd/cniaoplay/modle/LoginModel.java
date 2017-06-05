package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.LoginBean;
import com.chhd.cniaoplay.bean.request.LoginRequestBean;

import rx.Observable;

/**
 * Created by CWQ on 2017/5/28.
 */

public interface LoginModel {

    Observable<BaseBean<LoginBean>> login(String num, String pwd);
}
