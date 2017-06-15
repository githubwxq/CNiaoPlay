package com.chhd.cniaoplay.ui.fragment.download_manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chhd.cniaoplay.bean.InstalledApp;
import com.chhd.cniaoplay.ui.adapter.InstalledAppAdapter;
import com.chhd.cniaoplay.ui.base.SimpleDownloadManagerFragment;

import java.util.Iterator;
import java.util.List;

/**
 * Created by CWQ on 2017/6/13.
 */

public class InstalledAppFragment extends SimpleDownloadManagerFragment {

    public static InstalledAppFragment newInstance(String title) {
        InstalledAppFragment fragment = new InstalledAppFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");
        getActivity().registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getInstalledAppsData();
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String packageName = intent.getDataString().split(":")[1];

            InstalledAppAdapter adapter = (InstalledAppAdapter) recyclerView.getAdapter();
//            for (Iterator iterator = adapter.getData().iterator(); iterator.hasNext(); ) {
//                InstalledApp app = (InstalledApp) iterator.next();
//                if (app.getPackageName().equals(packageName)) {
//                    int position = adapter.getData().indexOf(app);
//                    iterator.remove();
//                    adapter.notifyItemRemoved(position);
//                }
//            }

            Iterator iterator = adapter.getData().iterator();
            while (iterator.hasNext()) {
                InstalledApp app = (InstalledApp) iterator.next();
                if (app.getPackageName().equals(packageName)) {
                    int position = adapter.getData().indexOf(app);
                    iterator.remove();
                    adapter.notifyItemRemoved(position);
                }
            }
        }
    };
}
