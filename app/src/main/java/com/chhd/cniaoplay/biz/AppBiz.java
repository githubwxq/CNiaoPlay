package com.chhd.cniaoplay.biz;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.chhd.cniaoplay.bean.InstalledApp;
import com.chhd.per_library.util.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CWQ on 2017/6/13.
 */

public class AppBiz {

    public static List<InstalledApp> getInstalledApps() {
        List<InstalledApp> apps = new ArrayList<>();
        PackageManager pm = UiUtils.getContext().getPackageManager();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(0);
        for (PackageInfo info : packageInfos) {
            InstalledApp app = new InstalledApp();

            app.setPackageName(info.packageName);
            app.setVersionCode(info.versionCode);
            app.setVersionName(info.versionName);
            app.setLastUpdateTime(info.lastUpdateTime);

            ApplicationInfo applicationInfo = info.applicationInfo;
            app.setIcon(applicationInfo.loadIcon(pm));
            app.setAppName(applicationInfo.loadLabel(pm).toString());

            app.setSystem((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)
                    == ApplicationInfo.FLAG_SYSTEM);

            apps.add(app);
        }
        return apps;
    }
}
