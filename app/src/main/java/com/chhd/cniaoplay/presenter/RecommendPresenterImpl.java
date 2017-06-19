package com.chhd.cniaoplay.presenter;

import android.util.Log;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.RecommendBean;
import com.chhd.cniaoplay.http.rx.RxHelper;
import com.chhd.cniaoplay.modle.RecommendModel;
import com.chhd.cniaoplay.http.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.http.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.view.RecommendView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.rx_cache2.Reply;

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

        model.getCacheData()
                .compose(RxHelper.<Reply<BaseBean<PageBean<AppInfo>>>>io_main())
                .subscribe(new Observer<Reply<BaseBean<PageBean<AppInfo>>>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Reply<BaseBean<PageBean<AppInfo>>> baseBeanReply) {
                        Log.i("debug", "--- getSource ---" + baseBeanReply.getSource());
                        Log.i("debug", "--- size ---" + baseBeanReply.getData().getData().getDatas().size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
