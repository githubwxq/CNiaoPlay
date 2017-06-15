package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.AppScope;
import com.chhd.cniaoplay.inject.module.MainModule;
import com.chhd.cniaoplay.inject.module.RecommendModule;
import com.chhd.cniaoplay.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by CWQ on 2017/6/13.
 */
@AppScope
@Component(modules = {MainModule.class}, dependencies = {AppComponent.class})
public interface MainComponent {

    void inject(MainActivity activity);
}
