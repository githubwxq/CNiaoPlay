package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.modle.AppInfoModel;
import com.chhd.cniaoplay.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.rx.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.view.AppInfoView;

import rx.Observable;

/**
 * Created by CWQ on 2017/5/26.
 */

public class AppInfoPresenterImpl implements AppInfoPresenter {

    private AppInfoView view;
    private AppInfoModel model;

    public static final int CATEGORY_FEATURED = 0;
    public static final int CATEGORY_TOPLIST = 1;
    public static final int CATEGORY_NEWLIST = 2;

    public AppInfoPresenterImpl(AppInfoView view, AppInfoModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestRankData(int page) {
        model.getRankData(page)
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new SimpleSubscriber<PageBean<AppInfo>>(view) {

                    @Override
                    public void success(PageBean<AppInfo> pageBean) {
                        view.showData(pageBean);
                    }
                });
    }

    @Override
    public void requestGameData(int page) {
        model.getGameData(page)
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new SimpleSubscriber<PageBean<AppInfo>>(view) {

                    @Override
                    public void success(PageBean<AppInfo> pageBean) {
                        view.showData(pageBean);
                    }
                });
    }

    @Override
    public void requestAppDataByCategory(int type, int categoryId, int page) {
        Observable<BaseBean<PageBean<AppInfo>>> observable = null;
        switch (type) {
            case CATEGORY_FEATURED:
                observable = model.getFeaturedAppDataByCategory(categoryId, page);
                break;
            case CATEGORY_TOPLIST:
                observable = model.getTopListAppDataByCategory(categoryId, page);
                break;
            case CATEGORY_NEWLIST:
                observable = model.getNewListAppDataByCategory(categoryId, page);
                break;
        }
        observable.compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new SimpleSubscriber<PageBean<AppInfo>>(view) {

                    @Override
                    public void success(PageBean<AppInfo> pageBean) {
                        view.showData(pageBean);
                    }
                });
    }

}
