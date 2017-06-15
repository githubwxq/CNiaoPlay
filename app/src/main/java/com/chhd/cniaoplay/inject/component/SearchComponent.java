package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.AppScope;
import com.chhd.cniaoplay.inject.module.SearchModule;
import com.chhd.cniaoplay.ui.activity.SearchActivity;

import dagger.Component;

/**
 * Created by CWQ on 2017/6/15.
 */
@AppScope
@Component(modules = {SearchModule.class}, dependencies = {AppComponent.class})
public interface SearchComponent {

    void inject(SearchActivity activity);
}
