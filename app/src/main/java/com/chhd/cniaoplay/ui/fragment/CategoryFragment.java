package com.chhd.cniaoplay.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.ui.base.LazyFragment;

public class CategoryFragment extends LazyFragment {

    public CategoryFragment() {
    }

    public static CategoryFragment newInstance(String title) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_category;
    }

    @Override
    protected void lazyLoad() {

    }
}
