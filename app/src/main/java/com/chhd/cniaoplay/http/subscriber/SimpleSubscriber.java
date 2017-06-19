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

    private int delayMillis = 0;
    private long startTimeMillis;

    private BaseView view;

    private ProgressDialog dialog;

    public SimpleSubscriber() {
    }

    public SimpleSubscriber(BaseView view) {
        this.view = view;
    }

    @Override
    public final void onSubscribe(@NonNull Disposable d) {
        startTimeMillis = System.currentTimeMillis();
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
        delayExcute(new NextRun(t));
    }

    private class NextRun implements Runnable {

        private T t;

        public NextRun(T t) {
            this.t = t;
        }

        @Override
        public void run() {
            if (t instanceof String) {
                LoggerUtils.i("json: " + t);
            }
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            if (view != null) {
                view.showSuccess();
            }
            success(t);
            after();
        }
    }

    @Override
    public final void onComplete() {

    }

    @Override
    public final void onError(Throwable e) {
        delayExcute(new ErrorRun(e));
    }

    private class ErrorRun implements Runnable {

        private Throwable e;

        public ErrorRun(Throwable e) {
            this.e = e;
        }

        @Override
        public void run() {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            if (view != null) {
                view.showError();
            }
            error(e);
            after();
        }
    }

    private void delayExcute(final Runnable r) {
        long timeDif = getTimeDif();
        if (timeDif > delayMillis) {
            new Handler().post(r);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    new Handler().post(r);
                }
            }, delayMillis - timeDif);
        }
    }

    private long getTimeDif() {
        return System.currentTimeMillis() - startTimeMillis;
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
