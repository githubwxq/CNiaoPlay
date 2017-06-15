package com.chhd.cniaoplay.inject.component;

import android.app.Application;

import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.inject.module.AppModule;
import com.chhd.cniaoplay.inject.module.DownloadModule;
import com.chhd.cniaoplay.inject.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by CWQ on 2017/6/10.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class, DownloadModule.class})
public interface AppComponent {

    Application getApplication();

    ApiService getApiService();

    RxDownload getRxDownload();

}
