package com.chhd.cniaoplay.inject.component;

import android.app.Activity;

import com.chhd.cniaoplay.inject.module.HttpModule;
import com.chhd.cniaoplay.inject.module.LoginModule;
import com.chhd.cniaoplay.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by CWQ on 2017/5/29.
 */
@Component(modules = {LoginModule.class, HttpModule.class})
public interface LoginComponent {

    void inject(LoginActivity activity);
}
