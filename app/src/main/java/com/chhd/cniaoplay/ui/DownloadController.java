package com.chhd.cniaoplay.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.Toast;

import com.chhd.cniaoplay.bean.AppDownloadInfo;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.http.RetrofitProvider;
import com.chhd.cniaoplay.http.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.http.rx.RxHelper;
import com.chhd.cniaoplay.http.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.util.LoggerUtils;
import com.chhd.per_library.util.ToastUtils;
import com.chhd.per_library.util.UiUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadBean;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * Created by CWQ on 2017/6/9.
 */

public class DownloadController {

    private RxDownload rxDownload;

    public DownloadController() {
        this.rxDownload = RxDownload.getInstance(UiUtils.getContext());
    }

    public void bindData(final Button btn, final AppInfo appInfo) {
        RetrofitProvider.createApi(ApiService.class)
                .getAppDownloadInfoData(appInfo.getId())
                .compose(RxHttpReponseCompat.<AppDownloadInfo>compatResult())
                .flatMap(new Function<AppDownloadInfo, ObservableSource<DownloadEvent>>() {

                    @Override
                    public ObservableSource<DownloadEvent> apply(@NonNull AppDownloadInfo appDownloadInfo) throws Exception {
                        appInfo.setAppDownloadInfo(appDownloadInfo);
                        return rxDownload.receiveDownloadStatus(appDownloadInfo.getDownloadUrl());
                    }

                })
                .compose(RxHelper.<DownloadEvent>io_main())
                .subscribe(new DownloadSubscriber(btn, appInfo));
    }

    private class DownloadSubscriber extends SimpleSubscriber<DownloadEvent> {

        private Button btn;
        private AppInfo appInfo;

        public DownloadSubscriber(Button btn, AppInfo appInfo) {
            this.btn = btn;
            this.appInfo = appInfo;
        }

        @Override
        public void success(DownloadEvent downloadEvent) {
            AppInfo appInfo = (AppInfo) btn.getTag();
            if (this.appInfo.getId() == appInfo.getId()) {
                int flag = downloadEvent.getFlag();
                bindClick(downloadEvent, btn, appInfo);
                switch (flag) {
                    case DownloadFlag.NORMAL:
                        btn.setText("下载");
                        break;
                    case DownloadFlag.WAITING:
                        btn.setText("等待");
                        break;
                    case DownloadFlag.STARTED:// 正在下载...
//                    btn.setText("暂停");
                        btn.setText(downloadEvent.getDownloadStatus().getPercentNumber() + "%");
                        break;
                    case DownloadFlag.PAUSED:
                        btn.setText("继续");
                        break;
                    case DownloadFlag.COMPLETED:
                        btn.setText("安装");
                        break;
                    case DownloadFlag.FAILED:
                        btn.setText("失败");
                        break;
                    case DownloadFlag.DELETED:
                        btn.setText("下载");
                        break;
                }
            }
        }

        @Override
        protected void error(Throwable e) {
            LoggerUtils.e(e);
        }
    }

    private void bindClick(final DownloadEvent downloadEvent, final Button btn, final AppInfo appInfo) {
        RxView.clicks(btn).subscribe(new Consumer<Object>() {

            @Override
            public void accept(@NonNull Object o) throws Exception {
                int flag = downloadEvent.getFlag();
                switch (flag) {
                    case DownloadFlag.NORMAL:
//                        ToastUtils.makeText("下载");
                        start(btn, appInfo);
                        break;
                    case DownloadFlag.WAITING:
//                        ToastUtils.makeText("等待");
                        pause(btn, appInfo);
                        break;
                    case DownloadFlag.STARTED:
                        pause(btn, appInfo);
                        break;
                    case DownloadFlag.PAUSED:
                        start(btn, appInfo);
                        break;
                    case DownloadFlag.COMPLETED:
//                        ToastUtils.makeText("安装");
                        install(btn, appInfo);
                        break;
                    case DownloadFlag.FAILED:
                        ToastUtils.makeText("失败");
                        break;
                    case DownloadFlag.DELETED:
                        ToastUtils.makeText("下载");
                        break;
                    default:
                        ToastUtils.makeText("未知");
                        break;
                }
            }
        });
    }

    private void install(Button btn, AppInfo appInfo) {
        File[] files = rxDownload.getRealFiles(appInfo.getAppDownloadInfo().getDownloadUrl());
        if (files != null) {
            Uri uri = Uri.fromFile(files[0]);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            btn.getContext().startActivity(intent);
        } else {
            Toast.makeText(btn.getContext(), "File not exists", Toast.LENGTH_LONG).show();
        }
    }

    private void pause(final Button btn, final AppInfo appInfo) {
        AppDownloadInfo appDownloadInfo = appInfo.getAppDownloadInfo();
        if (appDownloadInfo != null) {
            rxDownload
                    .pauseServiceDownload(appDownloadInfo.getDownloadUrl())
                    .subscribe();
        }
    }

    private void start(final Button btn, final AppInfo appInfo) {
        LoggerUtils.i("start -> DownloadUrl: " + appInfo.getAppDownloadInfo().getDownloadUrl());
        new RxPermissions((Activity) btn.getContext())
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {

                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            if (appInfo.getAppDownloadInfo() != null) {
                                rxDownload
                                        .serviceDownload(appInfo2DownloadBean(appInfo))
                                        .subscribe();
                            }
                        }
                    }
                });
    }


    public static DownloadBean appInfo2DownloadBean(AppInfo appInfo) {
        DownloadBean downloadBean = new DownloadBean();
        downloadBean.setUrl(appInfo.getAppDownloadInfo().getDownloadUrl());
        downloadBean.setExtra1(appInfo.getIcon());
        downloadBean.setExtra2(appInfo.getDisplayName());
        downloadBean.setExtra3(appInfo.getPublisherName());
        downloadBean.setExtra4(appInfo.getApkSize() + "");
        downloadBean.setExtra5(appInfo.getId() + "");
        return downloadBean;
    }

    public static AppInfo downloadRecord2AppInfo(DownloadRecord downloadRecord) {
        AppInfo appInfo = new AppInfo();
        appInfo.setId(Integer.parseInt(downloadRecord.getExtra5()));
        appInfo.setIcon(downloadRecord.getExtra1());
        appInfo.setDisplayName(downloadRecord.getExtra2());
        appInfo.setPublisherName(downloadRecord.getExtra3());
        appInfo.setApkSize(Integer.parseInt(downloadRecord.getExtra4()));
        AppDownloadInfo downloadInfo = new AppDownloadInfo();
        downloadInfo.setDownloadUrl(downloadRecord.getUrl());
        appInfo.setAppDownloadInfo(downloadInfo);
        return appInfo;
    }
}
