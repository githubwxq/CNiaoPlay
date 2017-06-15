package com.chhd.cniaoplay.inject.module;

import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.SubjectModel;
import com.chhd.cniaoplay.modle.SubjectModelImpl;
import com.chhd.cniaoplay.view.SubjectDetailView;
import com.chhd.cniaoplay.view.SubjectView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CWQ on 2017/6/14.
 */

@Module
public class SubjectDetailModule {

    private SubjectDetailView view;

    public SubjectDetailModule(SubjectDetailView view) {
        this.view = view;
    }

    @Provides
    public SubjectDetailView provideView() {
        return view;
    }

    @Provides
    public SubjectModel provideModel(ApiService apiService) {
        return new SubjectModelImpl(apiService);
    }
}
