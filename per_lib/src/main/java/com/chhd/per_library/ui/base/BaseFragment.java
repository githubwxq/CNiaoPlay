package com.chhd.per_library.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by CWQ on 2017/3/21.
 */

public abstract class BaseFragment extends Fragment {

    protected View rootView;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(getLayoutResID(), container, false);

        bind = ButterKnife.bind(this, rootView);

        return rootView;
    }

    public abstract int getLayoutResID();

    @Override
    public void onDestroy() {
        super.onDestroy();

        bind.unbind();
    }
}
