package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.AppScope;
import com.chhd.cniaoplay.inject.module.AppInfoModule;
import com.chhd.cniaoplay.ui.base.SimpleAppInfoFragment;

import dagger.Component;

/**
 * Created by CWQ on 2017/5/26.
 */
@AppScope
@Component(modules = {AppInfoModule.class}, dependencies = {AppComponent.class})
public interface AppInfoComponent {

    void inject(SimpleAppInfoFragment fragment);
}
