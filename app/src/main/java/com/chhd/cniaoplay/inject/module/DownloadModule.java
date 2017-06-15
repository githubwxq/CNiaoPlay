package com.chhd.cniaoplay.inject.module;

import android.app.Application;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by CWQ on 2017/6/10.
 */

@Module
public class DownloadModule {

    @Provides
    @Singleton
    public RxDownload provideRxDownload(Application application) {
        return RxDownload.getInstance(application);
    }
}
