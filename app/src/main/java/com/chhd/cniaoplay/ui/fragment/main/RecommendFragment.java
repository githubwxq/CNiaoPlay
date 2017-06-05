package com.chhd.cniaoplay.ui.fragment.main;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.RecommendBean;
import com.chhd.cniaoplay.inject.component.DaggerRecommendComponent;
import com.chhd.cniaoplay.inject.module.HttpModule;
import com.chhd.cniaoplay.inject.module.RecommendModule;
import com.chhd.cniaoplay.presenter.RecommendPresenter;
import com.chhd.cniaoplay.ui.adapter.RecommendAdapter;
import com.chhd.cniaoplay.ui.base.SimpleMainFragment;
import com.chhd.cniaoplay.view.RecommendView;

import java.util.List;

import javax.inject.Inject;

public class RecommendFragment extends SimpleMainFragment implements RecommendView {


    private RecommendAdapter adapter;

    public RecommendFragment() {
    }

    @Inject
    RecommendPresenter presenter;

    public static RecommendFragment newInstance(String title) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void lazyLoad() {
        super.lazyLoad();

        DaggerRecommendComponent
                .builder()
                .httpModule(new HttpModule())
                .recommendModule(new RecommendModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        super.initView();
        adapter = new RecommendAdapter(getActivity(), null, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void showData(List<AppInfo> list) {
    }

    @Override
    public void showRecommendData(RecommendBean bean) {
        adapter.setRecommendBean(bean);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        presenter.requestRecommendData();
    }
}
