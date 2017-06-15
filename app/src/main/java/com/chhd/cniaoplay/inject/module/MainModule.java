package com.chhd.cniaoplay.inject.module;

import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.AppInfoModelImpl;
import com.chhd.cniaoplay.modle.MainModel;
import com.chhd.cniaoplay.modle.MainModelImpl;
import com.chhd.cniaoplay.presenter.MainPresenter;
import com.chhd.cniaoplay.view.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CWQ on 2017/6/13.
 */

@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    public MainView provideView() {
        return view;
    }

    @Provides
    public MainModel provideModel(ApiService apiService) {
        return new MainModelImpl(apiService);
    }
}
