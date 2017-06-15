package com.chhd.cniaoplay.modle;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.chhd.cniaoplay.bean.InstalledApp;
import com.chhd.cniaoplay.biz.AppBiz;
import com.chhd.per_library.util.UiUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by CWQ on 2017/6/10.
 */

public class DownloadManagerModelImpl implements DownloadManagerModel {

    private RxDownload rxDownload;

    public DownloadManagerModelImpl(RxDownload rxDownload) {
        this.rxDownload = rxDownload;
    }

    @Override
    public Observable<List<DownloadRecord>> getDownloadRecordData() {
        return rxDownload.getTotalDownloadRecords();
    }

    @Override
    public Observable<List<InstalledApp>> getInstalledAppsData() {
        return Observable.just(AppBiz.getInstalledApps());
    }

}
