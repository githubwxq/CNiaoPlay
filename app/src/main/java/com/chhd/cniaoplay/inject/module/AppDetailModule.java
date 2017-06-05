package com.chhd.cniaoplay.inject.module;

import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.AppDetailModel;
import com.chhd.cniaoplay.modle.AppDetailModelImpl;
import com.chhd.cniaoplay.presenter.AppDetailPresenter;
import com.chhd.cniaoplay.presenter.AppDetailPresenterImpl;
import com.chhd.cniaoplay.view.AppDetailView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CWQ on 2017/6/4.
 */
@Module
public class AppDetailModule {

    private AppDetailView view;

    public AppDetailModule(AppDetailView view) {
        this.view = view;
    }

    @Provides
    public AppDetailPresenter providePresenter(AppDetailView view, AppDetailModel model) {
        return new AppDetailPresenterImpl(view, model);
    }

    @Provides
    public AppDetailView provideView() {
        return view;
    }

    @Provides
    public AppDetailModel provideModel(ApiService apiService) {
        return new AppDetailModelImpl(apiService);
    }
}
