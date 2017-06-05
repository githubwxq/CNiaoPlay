package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.RecommendBean;
import com.chhd.cniaoplay.modle.RecommendModel;
import com.chhd.cniaoplay.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.rx.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.view.RecommendView;

/**
 * Created by CWQ on 2017/5/9.
 */

public class RecommendPresenterImpl implements RecommendPresenter {

    private RecommendView view;
    private RecommendModel model;

    public RecommendPresenterImpl(RecommendView view, RecommendModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestData() {
        model.getData()
                .compose(RxHttpReponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new SimpleSubscriber<PageBean<AppInfo>>(view) {

                    @Override
                    public void success(PageBean<AppInfo> pageBean) {
                        view.showData(pageBean.getDatas());

                        if (pageBean.getDatas().isEmpty()) {
                            view.showEmpty();
                        }
                    }

                });
    }

    @Override
    public void requestRecommendData() {
        model.getRecommendData()
                .compose(RxHttpReponseCompat.<RecommendBean>compatResult())
                .subscribe(new SimpleSubscriber<RecommendBean>(view) {

                    @Override
                    public void success(RecommendBean bean) {
                        view.showRecommendData(bean);
                    }
                });
    }

}
