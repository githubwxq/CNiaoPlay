package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.AppScope;
import com.chhd.cniaoplay.inject.module.CategoryModule;
import com.chhd.cniaoplay.ui.fragment.main.CategoryFragment;

import dagger.Component;

/**
 * Created by CWQ on 2017/6/1.
 */
@AppScope
@Component(modules = {CategoryModule.class}, dependencies = {AppComponent.class})
public interface CategoryComponent {

    void inject(CategoryFragment fragment);
}
