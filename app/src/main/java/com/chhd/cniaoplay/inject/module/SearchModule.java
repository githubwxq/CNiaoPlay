package com.chhd.cniaoplay.inject.module;

import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.SearchModel;
import com.chhd.cniaoplay.modle.SearchModelImpl;
import com.chhd.cniaoplay.view.SearchView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CWQ on 2017/6/14.
 */

@Module
public class SearchModule {

    private SearchView view;

    public SearchModule(SearchView view) {
        this.view = view;
    }

    @Provides
    public SearchView provideView() {
        return view;
    }

    @Provides
    public SearchModel provideModel(ApiService apiService) {
        return new SearchModelImpl(apiService);
    }
}
