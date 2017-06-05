package com.chhd.cniaoplay.ui.fragment.main;

import android.os.Bundle;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.ui.adapter.AppAdatper;
import com.chhd.cniaoplay.ui.base.SimpleAppInfoFragment;

import java.util.ArrayList;

/**
 * Created by CWQ on 2017/5/27.
 */

public class GameFragment extends SimpleAppInfoFragment {


    public GameFragment() {
    }

    public static GameFragment newInstance(String title) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onRefresh() {
        super.onRefresh();

        presenter.requestGameData(page);
    }

    @Override
    public AppAdatper buildAdapter() {
        return AppAdatper.builder().showPosition(false).build();
    }

}
