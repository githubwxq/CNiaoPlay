package com.chhd.cniaoplay.rx;

import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.http.ApiException;
import com.chhd.cniaoplay.util.LoggerUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by CWQ on 2017/5/11.
 */

public class RxHttpReponseCompat {

    public static <T> ObservableTransformer<BaseBean<T>, T> compatResult() {

        return new ObservableTransformer<BaseBean<T>, T>() {

            @Override
            public ObservableSource<T> apply(@NonNull Observable<BaseBean<T>> observable) {

                return observable.flatMap(new Function<BaseBean<T>, ObservableSource<T>>() {

                    @Override
                    public ObservableSource<T> apply(@NonNull final BaseBean<T> baseBean) throws Exception {

                        if (baseBean.isRequestSuccess()) {

                            return Observable.create(new ObservableOnSubscribe<T>() {

                                @Override
                                public void subscribe(@NonNull ObservableEmitter<T> subscriber) throws Exception {
                                    subscriber.onNext(baseBean.getData());
                                    subscriber.onComplete();
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