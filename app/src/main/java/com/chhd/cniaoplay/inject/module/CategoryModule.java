package com.chhd.cniaoplay.inject.module;

import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.CategoryModel;
import com.chhd.cniaoplay.modle.CategoryModelImpl;
import com.chhd.cniaoplay.presenter.CategoryPresenter;
import com.chhd.cniaoplay.presenter.CategoryPresenterImpl;
import com.chhd.cniaoplay.view.CategoryView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CWQ on 2017/6/1.
 */
@Module
public class CategoryModule {

    private CategoryView view;

    public CategoryModule(CategoryView view) {
        this.view = view;
    }

    @Provides
    public CategoryPresenter providePresenter(CategoryView view, CategoryModel model) {
        return new CategoryPresenterImpl(view, model);
    }

    @Provides
    public CategoryView provideView() {
        return view;
    }

    @Provides
    public CategoryModel provideModel(ApiService apiService) {
        return new CategoryModelImpl(apiService);
    }
}
