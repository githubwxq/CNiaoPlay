package com.chhd.cniaoplay.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chhd.per_library.ui.base.BaseFragment;

/**
 * Created by CWQ on 2017/4/9.
 */

/**
 * 懒加载Fragment
 */
public abstract class LazyFragment extends ProgressFragment {

    private boolean hasLazyLoad;
    private boolean hasViewCreate;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (hasViewCreate && isVisibleToUser && !hasLazyLoad) {
            lazyLoad();
            hasLazyLoad = true;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hasViewCreate = true;
        if (getUserVisibleHint() && !hasLazyLoad) {
            lazyLoad();
            hasLazyLoad = true;
        }
    }

    /**
     * 第一次可见才执行此方法
     */
    protected abstract void lazyLoad();
}
