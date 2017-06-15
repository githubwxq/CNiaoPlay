package com.chhd.cniaoplay.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.InstalledApp;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.inject.component.DaggerDownloadManagerComponent;
import com.chhd.cniaoplay.inject.module.DownloadManagerModule;
import com.chhd.cniaoplay.presenter.DownloadManagerPresenterImpl;
import com.chhd.cniaoplay.ui.adapter.AppAdatper;
import com.chhd.cniaoplay.ui.adapter.InstalledAppAdapter;
import com.chhd.cniaoplay.ui.fragment.download_manager.DownloadedFragment;
import com.chhd.cniaoplay.view.DownloadManagerView;
import com.chhd.per_library.ui.decoration.SpaceItemDecoration;
import com.chhd.per_library.util.UiUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by CWQ on 2017/6/11.
 */

public abstract class SimpleDownloadManagerFragment extends ProgressFragment implements
        DownloadManagerView {

    protected RecyclerView recyclerView;

    @Inject
    protected DownloadManagerPresenterImpl presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerDownloadManagerComponent.builder()
                .appComponent(App.appComponent)
                .downloadManagerModule(new DownloadManagerModule(this))
                .build().inject(this);
    }

    @Override
    public void findViewById() {
        recyclerView = ButterKnife.findById(contentView, R.id.recycler_view);
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_download_manager;
    }

    @Override
    public void showAppInfoData(List<AppInfo> appInfos) {
        AppAdatper adapter = AppAdatper.builder().showMore(true).build();
        adapter.setNewData(appInfos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SpaceItemDecoration(UiUtils.dp2px(SPACE_FOR_APP),
                SpaceItemDecoration.VERTICAL, true));
    }

    @Override
    public void showInstalledAppData(List<InstalledApp> apps) {
        InstalledAppAdapter adapter = new InstalledAppAdapter();
        adapter.setNewData(apps);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SpaceItemDecoration(UiUtils.dp2px(SPACE_FOR_APP),
                SpaceItemDecoration.VERTICAL, true));
    }
}
