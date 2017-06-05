package com.chhd.cniaoplay.inject.module;

import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.LoginModel;
import com.chhd.cniaoplay.modle.LoginModelImpl;
import com.chhd.cniaoplay.presenter.LoginPresenter;
import com.chhd.cniaoplay.presenter.LoginPresenterImpl;
import com.chhd.cniaoplay.view.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CWQ on 2017/5/29.
 */
@Module
public class LoginModule {

    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    public LoginPresenter providePresenter(LoginView view, LoginModel model) {
        return new LoginPresenterImpl(view, model);
    }

    @Provides
    public LoginView provideView() {
        return view;
    }

    @Provides
    public LoginModel provideModel(ApiService apiService) {
        return new LoginModelImpl(apiService);
    }

}
