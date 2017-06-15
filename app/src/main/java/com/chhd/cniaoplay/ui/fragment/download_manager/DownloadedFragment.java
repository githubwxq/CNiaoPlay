package com.chhd.cniaoplay.ui.fragment.download_manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.inject.component.DaggerDownloadManagerComponent;
import com.chhd.cniaoplay.inject.module.DownloadManagerModule;
import com.chhd.cniaoplay.presenter.DownloadManagerPresenterImpl;
import com.chhd.cniaoplay.ui.adapter.AppAdatper;
import com.chhd.cniaoplay.ui.base.ProgressFragment;
import com.chhd.cniaoplay.ui.base.SimpleDownloadManagerFragment;
import com.chhd.cniaoplay.view.DownloadManagerView;
import com.chhd.per_library.ui.decoration.SpaceItemDecoration;
import com.chhd.per_library.util.UiUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by CWQ on 2017/6/11.
 */

public class DownloadedFragment extends SimpleDownloadManagerFragment{

    public static DownloadedFragment newInstance(String title) {
        DownloadedFragment fragment = new DownloadedFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getDownloadedData();
    }

}
