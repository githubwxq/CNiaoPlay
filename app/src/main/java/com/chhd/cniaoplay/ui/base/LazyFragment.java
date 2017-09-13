package com.chhd.cniaoplay.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chhd.per_library.ui.base.BaseFragment;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by CWQ on 2017/4/9.
 */

/**
 * 懒加载Fragment
 */
public abstract class LazyFragment extends ProgressFragment {

    protected boolean hasLazyLoad;
    protected boolean hasViewCreate;

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

            Map<String, String> map = new TreeMap<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "" + entry.getValue());
            }

            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                String value = map.get(key);
            }
        }
    }

    /**
     * 第一次可见才执行此方法
     */
    protected abstract void lazyLoad();
}
