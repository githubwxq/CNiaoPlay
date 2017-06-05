package com.chhd.cniaoplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.presenter.AppInfoPresenter;
import com.chhd.cniaoplay.presenter.AppInfoPresenterImpl;
import com.chhd.cniaoplay.ui.adapter.AppAdatper;
import com.chhd.cniaoplay.ui.base.SimpleAppInfoFragment;
import com.chhd.per_library.util.UiUtils;

/**
 * Created by CWQ on 2017/6/1.
 */

public class CategoryAppFragment extends SimpleAppInfoFragment {

    private int type;
    private int categoryId;

    public static CategoryAppFragment newInstance(String title, int categoryId) {
        CategoryAppFragment fragment = new CategoryAppFragment();

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("categoryId", categoryId);

        int type = AppInfoPresenterImpl.CATEGORY_FEATURED;
        if (title.equals(UiUtils.getString(R.string.competitive_product))) {
            type = AppInfoPresenterImpl.CATEGORY_FEATURED;
        } else if (title.equals(UiUtils.getString(R.string.rank))) {
            type = AppInfoPresenterImpl.CATEGORY_TOPLIST;
        } else if (title.equals(UiUtils.getString(R.string.new_product))) {
            type = AppInfoPresenterImpl.CATEGORY_NEWLIST;
        }
        args.putInt("type", type);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
        categoryId = getArguments().getInt("categoryId");
    }

    @Override
    public AppAdatper buildAdapter() {
        if (type == AppInfoPresenterImpl.CATEGORY_TOPLIST) {
            return AppAdatper.builder().showPosition(true).build();
        } else {
            return AppAdatper.builder().showPosition(false).build();
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        presenter.requestAppDataByCategory(type, categoryId, page);
    }
}
