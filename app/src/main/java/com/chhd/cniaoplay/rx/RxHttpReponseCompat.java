package com.chhd.cniaoplay.rx;

import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.http.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by CWQ on 2017/5/11.
 */

public class RxHttpReponseCompat {

    public static <T> Observable.Transformer<BaseBean<T>, T> compatResult() {

        return new Observable.Transformer<BaseBean<T>, T>() {

            @Override
            public Observable<T> call(Observable<BaseBean<T>> observable) {

                return observable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(final BaseBean<T> baseBean) {
                        if (baseBean.isRequestSuccess()) {
                            return Observable.create(new Observable.OnSubscribe<T>() {

                                @Override
                                public void call(Subscriber<? super T> subscriber) {
                                    subscriber.onNext(baseBean.getData());
                                    subscriber.onCompleted();
                                }
                            });
                        }
                        return Observable.error(new ApiException(baseBean.getStatus(), baseBean.getMessage()));

                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };


    }
}