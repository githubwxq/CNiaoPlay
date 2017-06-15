package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.AppScope;
import com.chhd.cniaoplay.inject.module.RecommendModule;
import com.chhd.cniaoplay.inject.module.SubjectModule;
import com.chhd.cniaoplay.modle.SubjectModel;
import com.chhd.cniaoplay.ui.activity.SubjectActivity;
import com.chhd.cniaoplay.ui.activity.SubjectDetailActivity;

import dagger.Component;

/**
 * Created by CWQ on 2017/6/14.
 */
@AppScope
@Component(modules = {SubjectModule.class}, dependencies = {AppComponent.class})
public interface SubjectComponent {

    void inject(SubjectActivity activity);
}
