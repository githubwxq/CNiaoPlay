package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.LoginBean;
import com.chhd.cniaoplay.bean.MessageEvent;
import com.chhd.cniaoplay.global.Action;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.modle.LoginModel;
import com.chhd.cniaoplay.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.rx.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.view.LoginView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by CWQ on 2017/5/29.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;
    private LoginModel model;

    public LoginPresenterImpl(LoginView view, LoginModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestLogin(String num, String password) {
        model.login(num, password)
                .compose(RxHttpReponseCompat.<LoginBean>compatResult())
                .subscribe(new SimpleSubscriber<LoginBean>(view) {

                    @Override
                    public void success(LoginBean loginBean) {
                        App.saveLoginInfo(loginBean.getToken(), loginBean.getUser());
                        EventBus.getDefault().post(new MessageEvent(Action.LOGIN));
                    }

                    @Override
                    protected boolean isShowDialog() {
                        return true;
                    }
                });
    }
}
