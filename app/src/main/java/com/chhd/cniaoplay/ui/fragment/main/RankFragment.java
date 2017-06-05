package com.chhd.cniaoplay.ui.fragment.main;

import android.os.Bundle;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.ui.adapter.AppAdatper;
import com.chhd.cniaoplay.ui.base.SimpleAppInfoFragment;

import java.util.ArrayList;

public class RankFragment extends SimpleAppInfoFragment {


    public RankFragment() {
    }

    public static RankFragment newInstance(String title) {
        RankFragment fragment = new RankFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onRefresh() {
        super.onRefresh();

        presenter.requestRankData(page);
    }

    @Override
    public AppAdatper buildAdapter() {
        return AppAdatper.builder().showPosition(true).build();
    }
}
