package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.InstalledApp;

import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by CWQ on 2017/6/10.
 */

public interface DownloadManagerModel {

    Observable<List<DownloadRecord>> getDownloadRecordData();

    Observable<List<InstalledApp>> getInstalledAppsData();
}
