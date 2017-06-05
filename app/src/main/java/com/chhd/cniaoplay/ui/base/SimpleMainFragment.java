package com.chhd.cniaoplay.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.MessageEvent;
import com.chhd.cniaoplay.global.Action;
import com.chhd.cniaoplay.ui.activity.MainActivity;
import com.chhd.cniaoplay.util.LoggerUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by CWQ on 2017/5/26.
 */

public abstract class SimpleMainFragment extends LazyFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout refreshLayout;

    @Override
    public int getContentResId() {
        return R.layout.fragment_simple_main;
    }

    @Override
    public void findViewById() {
        recyclerView = ButterKnife.findById(contentView, R.id.recycler_view);
        refreshLayout = ButterKnife.findById(contentView, R.id.refresh_layout);
    }

    @Override
    protected void lazyLoad() {

        initView();

        refresh();

        EventBus.getDefault().register(this);
    }

    protected void initView() {
        refreshLayout.setColorSchemeResources(R.color.colorAccent);
        refreshLayout.setOnRefreshListener(this);

        recyclerView.addOnScrollListener(onScrollListener);
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int totalDy = recyclerView.computeVerticalScrollOffset();
            if (totalDy > 0) {
            } else {
            }
        }
    };

    private void refresh() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                SimpleMainFragment.this.onRefresh();
            }
        });
    }

    @Override
    public void after() {
        if (refreshLayout.isRefreshing()) refreshLayout.setRefreshing(false);
    }

    @Override
    protected void retry() {
        super.retry();
        refresh();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getAction()) {
            case Action.LOGIN:
            case Action.LOGOUT:
                refresh();
                break;
        }
    }
}
