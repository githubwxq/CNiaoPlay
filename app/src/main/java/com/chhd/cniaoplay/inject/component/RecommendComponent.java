package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.module.HttpModule;
import com.chhd.cniaoplay.inject.module.RecommendModule;
import com.chhd.cniaoplay.ui.fragment.main.RecommendFragment;

import dagger.Component;

/**
 * Created by CWQ on 2017/5/9.
 */
@Component(modules = {RecommendModule.class, HttpModule.class})
public interface RecommendComponent {

    void inject(RecommendFragment fragment);
}
