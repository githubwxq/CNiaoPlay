package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.AppScope;
import com.chhd.cniaoplay.inject.module.LoginModule;
import com.chhd.cniaoplay.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by CWQ on 2017/5/29.
 */
@AppScope
@Component(modules = {LoginModule.class}, dependencies = {AppComponent.class})
public interface LoginComponent {

    void inject(LoginActivity activity);
}
