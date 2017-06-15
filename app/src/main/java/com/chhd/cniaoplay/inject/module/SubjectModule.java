package com.chhd.cniaoplay.inject.module;

import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.SubjectModel;
import com.chhd.cniaoplay.modle.SubjectModelImpl;
import com.chhd.cniaoplay.view.SubjectView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CWQ on 2017/6/14.
 */

@Module
public class SubjectModule {

    private SubjectView view;

    public SubjectModule(SubjectView view) {
        this.view = view;
    }

    @Provides
    public SubjectView provideView() {
        return view;
    }

    @Provides
    public SubjectModel provideModel(ApiService apiService) {
        return new SubjectModelImpl(apiService);
    }
}
