package com.chhd.cniaoplay.ui.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by CWQ on 2017/5/8.
 */

public abstract class SimpleAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    public SimpleAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);

        openLoadAnimation();
    }
}
