package com.chhd.cniaoplay.inject.module;

import com.chhd.cniaoplay.modle.RecommendModel;
import com.chhd.cniaoplay.modle.RecommendModelImpl;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.presenter.RecommendPresenter;
import com.chhd.cniaoplay.presenter.RecommendPresenterImpl;
import com.chhd.cniaoplay.view.RecommendView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CWQ on 2017/5/9.
 */
@Module
public class RecommendModule {

    private RecommendView view;

    public RecommendModule(RecommendView view) {
        this.view = view;
    }

    @Provides
    public RecommendPresenter providePresenter(RecommendView view, RecommendModel model) {
        return new RecommendPresenterImpl(view, model);
    }

    @Provides
    public RecommendView provideView() {
        return view;
    }

    @Provides
    public RecommendModel provideModel(ApiService apiService) {
        return new RecommendModelImpl(apiService);
    }

}
