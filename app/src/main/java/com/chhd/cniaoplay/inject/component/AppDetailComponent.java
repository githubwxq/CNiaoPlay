package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.AppScope;
import com.chhd.cniaoplay.inject.module.AppDetailModule;
import com.chhd.cniaoplay.ui.fragment.AppDetailFragment;

import dagger.Component;

/**
 * Created by CWQ on 2017/6/4.
 */
@AppScope
@Component(modules = {AppDetailModule.class}, dependencies = {AppComponent.class})
public interface AppDetailComponent {

    void inject(AppDetailFragment fragment);
}
