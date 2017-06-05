package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.module.CategoryModule;
import com.chhd.cniaoplay.inject.module.HttpModule;
import com.chhd.cniaoplay.ui.fragment.main.CategoryFragment;

import dagger.Component;

/**
 * Created by CWQ on 2017/6/1.
 */
@Component(modules = {CategoryModule.class, HttpModule.class})
public interface CategoryComponent {

    void inject(CategoryFragment fragment);
}
