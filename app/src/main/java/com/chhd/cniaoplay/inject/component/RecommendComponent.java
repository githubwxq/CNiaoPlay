package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.AppScope;
import com.chhd.cniaoplay.inject.module.RecommendModule;
import com.chhd.cniaoplay.ui.fragment.main.RecommendFragment;

import dagger.Component;

/**
 * Created by CWQ on 2017/5/9.
 */
@AppScope
@Component(modules = {RecommendModule.class}, dependencies = {AppComponent.class})
public interface RecommendComponent {

    void inject(RecommendFragment fragment);
}
