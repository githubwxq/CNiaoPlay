package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.AppInfoDetail;
import com.chhd.cniaoplay.modle.AppDetailModel;
import com.chhd.cniaoplay.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.rx.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.util.LoggerUtils;
import com.chhd.cniaoplay.view.AppDetailView;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CWQ on 2017/6/4.
 */

public class AppDetailPresenterImpl implements AppDetailPresenter {

    private AppDetailView view;
    private AppDetailModel model;

    public AppDetailPresenterImpl(AppDetailView view, AppDetailModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestAppDetailData(int id) {
        model.getAppDetailData(id)
                .compose(RxHttpReponseCompat.<AppInfoDetail>compatResult())
                .subscribe(new SimpleSubscriber<AppInfoDetail>(view) {

                    @Override
                    public void before() {
                        super.before();
                        view.showLoading();
                    }

                    @Override
                    public void success(AppInfoDetail appInfoDetail) {
                        view.showAppInfoDetailData(appInfoDetail);
                    }
                });
    }
}
