package com.chhd.cniaoplay.http.subscriber;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.app.Fragment;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.http.rx.RxErrorHandler;
import com.chhd.cniaoplay.util.LoggerUtils;
import com.chhd.cniaoplay.view.base.BaseView;
import com.chhd.per_library.util.UiUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * Created by CWQ on 2017/5/15.
 */

public abstract class SimpleSubscriber<T> implements Observer<T> {

    private BaseView view;

    private ProgressDialog dialog;

    public SimpleSubscriber() {
    }

    public SimpleSubscriber(BaseView view) {
        this.view = view;
    }

    @Override
    public final void onSubscribe(@NonNull Disposable d) {
        if (isShowDialog() && view != null) {
            Context context = null;
            if (view instanceof Fragment) {
                context = ((Fragment) view).getActivity();
            } else if (view instanceof Activity) {
                context = (Activity) view;
            }
            if (context != null) {
                dialog = new ProgressDialog(context);
                dialog.setMessage(UiUtils.getString(R.string.please_loading));
                dialog.setCanceledOnTouchOutside(false);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
                dialog.show();
            }
        }
        before();
    }

    @Override
    public void onNext(T t) {
        if (t instanceof String) {
            LoggerUtils.i("json: " + t);
        }
        if (view != null) {
            view.showSuccess();
        }
        success(t);
    }


    @Override
    public final void onComplete() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (view != null) {
            view.showSuccess();
        }
        after();
    }

    @Override
    public final void onError(Throwable e) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (view != null) {
            view.showError();
        }
        error(e);
        after();
    }


    public void before() {

    }

    public abstract void success(T t);

    protected void error(Throwable e) {
        RxErrorHandler.handlerError(e);
    }

    protected void after() {
        if (view != null) {
            view.after();
        }
    }

    protected boolean isShowDialog() {
        return false;
    }
}
