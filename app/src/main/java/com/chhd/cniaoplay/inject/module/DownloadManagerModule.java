package com.chhd.cniaoplay.inject.module;

import com.chhd.cniaoplay.modle.DownloadManagerModel;
import com.chhd.cniaoplay.modle.DownloadManagerModelImpl;
import com.chhd.cniaoplay.view.DownloadManagerView;

import dagger.Module;
import dagger.Provides;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by CWQ on 2017/6/10.
 */

@Module
public class DownloadManagerModule {

    private DownloadManagerView view;

    public DownloadManagerModule(DownloadManagerView view) {
        this.view = view;
    }

    @Provides
    public DownloadManagerView provideView() {
        return view;
    }

    @Provides
    public DownloadManagerModel provideModel(RxDownload rxDownload) {
        return new DownloadManagerModelImpl(rxDownload);
    }
}
