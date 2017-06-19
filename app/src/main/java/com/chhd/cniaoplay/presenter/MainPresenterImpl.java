package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.InstalledApp;
import com.chhd.cniaoplay.bean.request.AppUpdateParam;
import com.chhd.cniaoplay.biz.AppBiz;
import com.chhd.cniaoplay.modle.MainModel;
import com.chhd.cniaoplay.http.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.http.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.view.MainView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by CWQ on 2017/6/13.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private MainModel model;

    @Inject
    public MainPresenterImpl(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestAppUpdateInfoData() {
        Observable.just(buildParams(AppBiz.getInstalledApps()))
                .flatMap(new Function<AppUpdateParam, ObservableSource<List<AppInfo>>>() {

                    @Override
                    public ObservableSource<List<AppInfo>> apply(@NonNull AppUpdateParam param)
                            throws Exception {
                        return model
                                .getAppUpdateInfoData(param)
                                .compose(RxHttpReponseCompat.<List<AppInfo>>compatResult());
                    }
                })
                .subscribe(new SimpleSubscriber<List<AppInfo>>() {

                    @Override
                    public void success(List<AppInfo> infos) {

                    }
                });
    }

    private AppUpdateParam buildParams(List<InstalledApp> apks) {
        StringBuilder packageNameBuilder = new StringBuilder();
        StringBuilder versionCodeBuilder = new StringBuilder();
        for (InstalledApp apk : apks) {
            if (!apk.isSystem()) {
                packageNameBuilder.append(apk.getPackageName()).append(",");
                versionCodeBuilder.append(apk.getVersionCode()).append(",");
            }
        }
        AppUpdateParam param = new AppUpdateParam();
        param.setPackageName(packageNameBuilder.toString());
        param.setVersionCode(versionCodeBuilder.toString());
        return param;

    }
}
