package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.module.AppDetailModule;
import com.chhd.cniaoplay.inject.module.HttpModule;
import com.chhd.cniaoplay.ui.fragment.AppDetailFragment;
import com.chhd.cniaoplay.view.AppDetailView;

import butterknife.BindView;
import dagger.Component;
import dagger.Module;

/**
 * Created by CWQ on 2017/6/4.
 */
@Component(modules = {AppDetailModule.class, HttpModule.class})
public interface AppDetailComponent {

    void inject(AppDetailFragment fragment);
}
