package com.chhd.cniaoplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.Subject;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.inject.component.DaggerSubjectComponent;
import com.chhd.cniaoplay.inject.module.SubjectModule;
import com.chhd.cniaoplay.modle.SubjectModelImpl;
import com.chhd.cniaoplay.presenter.SubjectPresenterImpl;
import com.chhd.cniaoplay.ui.adapter.SubjectAdapter;
import com.chhd.cniaoplay.ui.base.ProgressActivity;
import com.chhd.cniaoplay.view.SubjectView;
import com.chhd.per_library.ui.decoration.GridSpaceItemDecoration;
import com.chhd.per_library.util.UiUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SubjectActivity extends ProgressActivity implements SubjectView {

    private Toolbar toolbar;
    protected SwipeRefreshLayout refreshLayout;
    protected RecyclerView recyclerView;

    @Inject
    SubjectPresenterImpl presenter;
    private SubjectAdapter adapter;

    @Override
    public void findViewById() {
        toolbar = ButterKnife.findById(contentView, R.id.toolbar);
        refreshLayout = ButterKnife.findById(contentView, R.id.refresh_layout);
        recyclerView = ButterKnife.findById(contentView, R.id.recycler_view);
    }

    @Override
    public int getContentResId() {
        return R.layout.activity_subject;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionbar();

        refreshLayout.setColorSchemeResources(R.color.colorAccent);
        refreshLayout.setOnRefreshListener(onRefreshListener);

        DaggerSubjectComponent.builder()
                .appComponent(App.appComponent)
                .subjectModule(new SubjectModule(this))
                .build().inject(this);

        refresh();
    }

    private int page = 0;

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout
            .OnRefreshListener() {

        @Override
        public void onRefresh() {
            presenter.requestSubjectData(page);
        }
    };

    private void refresh() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                onRefreshListener.onRefresh();
            }
        });
    }

    private void initActionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.hot_theme);
    }

    @Override
    public void after() {
        super.after();
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected void retry() {
        super.retry();
        refresh();
    }

    @Override
    public void showData(PageBean<Subject> pageBean) {
        adapter = new SubjectAdapter();
        adapter.setNewData(pageBean.getDatas());
        adapter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new GridSpaceItemDecoration(2, UiUtils.dp2px(10), true));
    }

    private BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter
            .OnItemClickListener() {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(getApplicationContext(), SubjectDetailActivity.class);
            intent.putExtra("subject", SubjectActivity.this.adapter.getData().get(position));
            startActivity(intent);
        }
    };
}
