package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.module.HttpModule;
import com.chhd.cniaoplay.inject.module.AppInfoModule;
import com.chhd.cniaoplay.ui.base.SimpleAppInfoFragment;

import dagger.Component;

/**
 * Created by CWQ on 2017/5/26.
 */
@Component(modules = {AppInfoModule.class, HttpModule.class})
public interface AppInfoComponent {

    void inject(SimpleAppInfoFragment fragment);
}
