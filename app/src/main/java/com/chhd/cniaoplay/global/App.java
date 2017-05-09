package com.chhd.cniaoplay.global;

import com.chhd.per_library.global.BaseApplication;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by CWQ on 2017/5/4.
 */

public class App extends BaseApplication implements Constant {

    @Override
    public void onCreate() {
        super.onCreate();

        if (Config.isDebug) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }

        Logger.init(TAG).methodOffset(1);

    }
}
