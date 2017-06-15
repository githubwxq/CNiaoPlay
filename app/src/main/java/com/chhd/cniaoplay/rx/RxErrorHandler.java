package com.chhd.cniaoplay.rx;

import android.content.Intent;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.http.ApiException;
import com.chhd.cniaoplay.ui.activity.LoginActivity;
import com.chhd.cniaoplay.util.LoggerUtils;
import com.chhd.per_library.util.ToastUtils;
import com.chhd.per_library.util.UiUtils;

/**
 * Created by CWQ on 2017/5/16.
 */

public class RxErrorHandler {

    public static Throwable handlerError(Throwable e) {
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            handlerApiEx(apiException);
        } else {
            LoggerUtils.e(e);
        }
        return e;
    }

    private static void handlerApiEx(ApiException apiException) {
        switch (apiException.getCode()) {
            case 10010:// token 丢失
            case 10011:// token 错误或丢失
                Intent intent = new Intent(UiUtils.getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                UiUtils.getContext().startActivity(intent);
                if (App.user != null) {
                    ToastUtils.makeText(UiUtils.getString(R.string.please_again_login));
                } else {
                    ToastUtils.makeText(UiUtils.getString(R.string.please_login));
                }
                break;
            default:
                ToastUtils.makeText(apiException.getDisplayMessage());
                break;
        }
    }
}
