package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.InstalledApp;
import com.chhd.cniaoplay.rx.RxHelper;
import com.chhd.cniaoplay.rx.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.ui.DownloadController;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.modle.DownloadManagerModel;
import com.chhd.cniaoplay.view.DownloadManagerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by CWQ on 2017/6/10.
 */

public class DownloadManagerPresenterImpl implements DownloadManagerPresenter {

    private DownloadManagerView view;
    private DownloadManagerModel model;

    @Inject
    public DownloadManagerPresenterImpl(DownloadManagerView view, DownloadManagerModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getDownloadingData() {
        model.getDownloadRecordData()
                .compose(RxHelper.<List<DownloadRecord>>io_main())
                .subscribe(new Consumer<List<DownloadRecord>>() {

                    @Override
                    public void accept(@NonNull List<DownloadRecord> downloadRecords) throws Exception {
                        view.showAppInfoData(transformDownloading(downloadRecords));
                    }
                });

    }

    @Override
    public void getDownloadedData() {
        model.getDownloadRecordData()
                .compose(RxHelper.<List<DownloadRecord>>io_main())
                .subscribe(new Consumer<List<DownloadRecord>>() {

                    @Override
                    public void accept(@NonNull List<DownloadRecord> downloadRecords) throws Exception {
                        view.showAppInfoData(transformDownloaded(downloadRecords));
                    }
                });
    }

    @Override
    public void getInstalledAppsData() {
        model.getInstalledAppsData()
                .flatMap(new Function<List<InstalledApp>, ObservableSource<List<InstalledApp>>>() {
                    @Override
                    public ObservableSource<List<InstalledApp>> apply(@NonNull List<InstalledApp> apps)
                            throws Exception {
                        List<InstalledApp> userApps = new ArrayList<>();
                        for (InstalledApp app : apps) {
                            if (!app.isSystem()) {
                                userApps.add(app);
                            }
                        }
                        return Observable.just(userApps);
                    }
                })
                .compose(RxHelper.<List<InstalledApp>>io_main())
                .subscribe(new SimpleSubscriber<List<InstalledApp>>() {
                    @Override
                    public void success(List<InstalledApp> apps) {
                        view.showInstalledAppData(apps);
                    }
                });

    }

    private List<AppInfo> transformDownloading(List<DownloadRecord> downloadRecords) {
        List<AppInfo> appInfos = new ArrayList<>();
        for (DownloadRecord downloadRecord : downloadRecords) {
            if (downloadRecord.getFlag() != DownloadFlag.COMPLETED) {
                appInfos.add(DownloadController.downloadRecord2AppInfo(downloadRecord));
            }
        }
        return appInfos;
    }

    private List<AppInfo> transformDownloaded(List<DownloadRecord> downloadRecords) {
        List<AppInfo> appInfos = new ArrayList<>();
        for (DownloadRecord downloadRecord : downloadRecords) {
            if (downloadRecord.getFlag() == DownloadFlag.COMPLETED) {
                appInfos.add(DownloadController.downloadRecord2AppInfo(downloadRecord));
            }
        }
        return appInfos;
    }
}
