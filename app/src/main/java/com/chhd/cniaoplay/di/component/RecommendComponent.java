package com.chhd.cniaoplay.di.component;

import com.chhd.cniaoplay.di.module.RemmendModule;
import com.chhd.cniaoplay.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by CWQ on 2017/5/9.
 */

@Component(modules = RemmendModule.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);
}
