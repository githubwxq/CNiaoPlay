package com.chhd.cniaoplay.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.view.base.BaseView;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class ProgressFragment extends SimpleFragment implements BaseView {

    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.content)
    FrameLayout content;

    protected View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        contentView = LayoutInflater.from(getActivity()).inflate(getContentResId(), content, true);

        findViewById();
    }

    public abstract void findViewById();

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

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_progress;
    }

    public abstract int getContentResId();

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
        for (int i = 0; i < root.getChildCount(); i++) {
            if (root.getChildAt(i).getId() == viewId) {
                root.getChildAt(i).setVisibility(View.VISIBLE);
            } else {
                root.getChildAt(i).setVisibility(View.INVISIBLE);
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
