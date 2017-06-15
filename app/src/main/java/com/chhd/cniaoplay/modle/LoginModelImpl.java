package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.LoginBean;
import com.chhd.cniaoplay.bean.request.LoginRequestParam;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.base.BaseModel;

import io.reactivex.Observable;

/**
 * Created by CWQ on 2017/5/28.
 */

public class LoginModelImpl extends BaseModel implements LoginModel {

    public LoginModelImpl(ApiService apiService) {
        super(apiService);
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String num, String pwd) {
        LoginRequestParam param = new LoginRequestParam();
        param.setEmail(num);
        param.setPassword(pwd);
        return apiService.login(param);
    }
}
