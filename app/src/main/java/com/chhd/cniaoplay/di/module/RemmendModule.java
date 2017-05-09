package com.chhd.cniaoplay.di.module;

import com.chhd.cniaoplay.data.RecommendModel;
import com.chhd.cniaoplay.presenter.RecommendPresenter;
import com.chhd.cniaoplay.presenter.contract.RecommendContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CWQ on 2017/5/9.
 */
@Module
public class RemmendModule {

    private RecommendContract.View view;

    public RemmendModule(RecommendContract.View view) {
        this.view = view;
    }

    @Provides
    public RecommendContract.Presenter providePresenter(RecommendContract.View view, RecommendModel model) {
        return new RecommendPresenter(view, model);
    }

    @Provides
    public RecommendContract.View provideView() {
        return view;
    }

    @Provides
    public RecommendModel provideModel() {
        return new RecommendModel();
    }
}
