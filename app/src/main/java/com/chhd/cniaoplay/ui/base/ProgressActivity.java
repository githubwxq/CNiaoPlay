package com.chhd.cniaoplay.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.MessageEvent;
import com.chhd.cniaoplay.global.Action;
import com.chhd.cniaoplay.view.base.BaseView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by CWQ on 2017/6/14.
 */

public abstract class ProgressActivity extends SimpleActivity implements BaseView {

    @BindView(R.id.loading)
    View loading;
    @BindView(R.id.empty)
    View empty;
    @BindView(R.id.error)
    View error;

    @BindView(R.id.content)
    FrameLayout content;

    private List<View> views = new ArrayList<>();

    protected View contentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        views.add(loading);
        views.add(empty);
        views.add(error);
        views.add(content);

        contentView = LayoutInflater.from(this).inflate(getContentResId(), content, true);

        findViewById();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getAction()) {
            case Action.LOGIN:
            case Action.LOGOUT:
                retry();
                break;
        }
    }

    public abstract void findViewById();

    @Override
    public int getLayoutResID() {
        return R.layout.activity_progress;
    }

    public abstract int getContentResId();


    @OnClick({R.id.btn_retry})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retry:
                retry();
                break;
        }
    }

    protected void retry() {
        showLoadingView();
    }

    protected void showContentView() {
        showStatusView(R.id.content);
    }

    protected void showLoadingView() {
        showStatusView(R.id.loading);
    }

    protected void showEmptyView() {
        showStatusView(R.id.empty);
    }

    protected void showErrorView() {
        showStatusView(R.id.error);
    }

    protected void showStatusView(int viewId) {
        for (View v : views) {
            if (v.getId() == viewId) {
                v.setVisibility(View.VISIBLE);
            } else {
                v.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void showLoading() {
        showLoadingView();
    }

    @Override
    public void showSuccess() {
        showContentView();
    }

    @Override
    public void showEmpty() {
        showEmptyView();
    }

    @Override
    public void showError() {
        showErrorView();
    }

    @Override
    public void after() {

    }
}
