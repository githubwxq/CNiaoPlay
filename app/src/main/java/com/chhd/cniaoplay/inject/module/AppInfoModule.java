package com.chhd.cniaoplay.inject.module;

import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.AppInfoModel;
import com.chhd.cniaoplay.modle.AppInfoModelImpl;
import com.chhd.cniaoplay.presenter.AppInfoPresenter;
import com.chhd.cniaoplay.presenter.AppInfoPresenterImpl;
import com.chhd.cniaoplay.view.AppInfoView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CWQ on 2017/5/26.
 */
@Module
public class AppInfoModule {

    private AppInfoView view;

    public AppInfoModule(AppInfoView view) {
        this.view = view;
    }

    @Provides
    public AppInfoPresenter providePresenter(AppInfoView view, AppInfoModel model) {
        return new AppInfoPresenterImpl(view, model);
    }

    @Provides
    public AppInfoView provideView() {
        return view;
    }

    @Provides
    public AppInfoModel provideModel(ApiService apiService) {
        return new AppInfoModelImpl(apiService);
    }
}
