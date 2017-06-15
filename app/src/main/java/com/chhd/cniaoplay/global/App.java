package com.chhd.cniaoplay.global;

import com.chhd.cniaoplay.bean.User;
import com.chhd.cniaoplay.inject.component.AppComponent;
import com.chhd.cniaoplay.inject.component.DaggerAppComponent;
import com.chhd.cniaoplay.inject.module.AppModule;
import com.chhd.cniaoplay.inject.module.HttpModule;
import com.chhd.cniaoplay.util.JsonUtils;
import com.chhd.per_library.global.BaseApplication;
import com.chhd.per_library.util.SpUtils;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by CWQ on 2017/5/4.
 */

public class App extends BaseApplication implements Constant {

    public static String token = "";
    public static User user;

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        if (Config.isDebug) {
//            if (LeakCanary.isInAnalyzerProcess(this)) {
//                // This process is dedicated to LeakCanary for heap analysis.
//                // You should not init your app in this process.
//                return;
//            }
//            LeakCanary.install(this);
//        }

        Logger.init(TAG).methodOffset(1);

        restoreLoginInfo();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();

    }

    public static void saveLoginInfo(String token, User user) {
        App.token = token;
        App.user = user;
        SpUtils.putString("token", token);
        SpUtils.putString("user", JsonUtils.toJSON(user));
    }

    public static void restoreLoginInfo() {
        App.token = SpUtils.getString("token");
        App.user = JsonUtils.fromJson(SpUtils.getString("user"), User.class);
    }

    public static void clearLoginInfo() {
        token = null;
        user = null;
        SpUtils.putString("token", "");
        SpUtils.putString("user", "");
    }
}
