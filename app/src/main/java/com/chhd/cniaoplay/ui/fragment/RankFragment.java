package com.chhd.cniaoplay.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.ui.base.LazyFragment;

public class RankFragment extends LazyFragment {

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
    public int getLayoutResID() {
        return R.layout.fragment_rank;
    }

    @Override
    protected void lazyLoad() {

    }
}
