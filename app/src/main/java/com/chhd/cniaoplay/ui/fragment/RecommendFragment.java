package com.chhd.cniaoplay.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.Page;
import com.chhd.cniaoplay.di.component.DaggerRecommendComponent;
import com.chhd.cniaoplay.di.module.RemmendModule;
import com.chhd.cniaoplay.http.Api;
import com.chhd.cniaoplay.http.HttpManager;
import com.chhd.cniaoplay.presenter.RecommendPresenter;
import com.chhd.cniaoplay.presenter.contract.RecommendContract;
import com.chhd.cniaoplay.ui.adapter.RecommendAppAdatper;
import com.chhd.cniaoplay.ui.base.LazyFragment;
import com.chhd.cniaoplay.util.LoggerUtil;
import com.chhd.per_library.ui.decoration.SpaceItemDecoration;
import com.chhd.per_library.util.UiUtil;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendFragment extends LazyFragment implements RecommendContract.View {

    @ViewInject(R.id.rv_app)
    RecyclerView rvApp;

    @Inject
    RecommendContract.Presenter presenter;

    private List<AppInfo> items = new ArrayList<>();
    private RecommendAppAdatper adapter;

    public RecommendFragment() {
    }

    public static RecommendFragment newInstance(String title) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void lazyLoad() {

        initView();

        DaggerRecommendComponent.builder().remmendModule(new RemmendModule(this))
                .build().inject(this);
        presenter.requestData();
    }

    private void initView() {
        adapter = new RecommendAppAdatper(items);
        rvApp.setAdapter(adapter);
        rvApp.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvApp.addItemDecoration(new SpaceItemDecoration(UiUtil.dp2px(8),
                SpaceItemDecoration.VERTICAL, true));
    }

    @Override
    public void showResult(List<AppInfo> list) {
        adapter.setNewData(list);
    }
}
