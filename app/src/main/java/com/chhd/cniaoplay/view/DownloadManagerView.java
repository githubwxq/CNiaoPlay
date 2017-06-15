package com.chhd.cniaoplay.view;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.InstalledApp;
import com.chhd.cniaoplay.view.base.BaseView;

import java.util.List;

import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by CWQ on 2017/6/10.
 */

public interface DownloadManagerView extends BaseView {

    void showAppInfoData(List<AppInfo> appInfos);

    void showInstalledAppData(List<InstalledApp> apps);
}
