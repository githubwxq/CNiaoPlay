package com.chhd.cniaoplay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.AppInfoDetail;
import com.chhd.cniaoplay.bean.MessageEvent;
import com.chhd.cniaoplay.global.Action;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.inject.component.DaggerAppDetailComponent;
import com.chhd.cniaoplay.inject.module.AppDetailModule;
import com.chhd.cniaoplay.presenter.AppDetailPresenterImpl;
import com.chhd.cniaoplay.ui.adapter.AppDetailAppAdapter;
import com.chhd.cniaoplay.ui.adapter.AppDetailPicAdapter;
import com.chhd.cniaoplay.ui.base.ProgressFragment;
import com.chhd.cniaoplay.view.AppDetailView;
import com.chhd.per_library.ui.decoration.SpaceItemDecoration;
import com.chhd.per_library.util.UiUtils;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by CWQ on 2017/6/4.
 */

public class AppDetailFragment extends ProgressFragment implements AppDetailView {

    @Inject
    AppDetailPresenterImpl presenter;

    private int id;
    private RecyclerView rvPic;
    private ExpandableTextView expandableTextView;
    private RecyclerView rvSame;
    private RecyclerView rvRelate;

    public static AppDetailFragment newInstance(int id) {
        AppDetailFragment fragment = new AppDetailFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DaggerAppDetailComponent.builder()
                .appComponent(App.appComponent)
                .appDetailModule(new AppDetailModule(this))
                .build().inject(this);

        presenter.requestAppDetailData(id);
    }

    @Override
    public void findViewById() {
        rvPic = ButterKnife.findById(contentView, R.id.rv_pic);
        expandableTextView = ButterKnife.findById(contentView, R.id.expand_text_view);
        rvSame = ButterKnife.findById(contentView, R.id.rv_same);
        rvRelate = ButterKnife.findById(contentView, R.id.rv_relate);
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_app_detail;
    }

    @Override
    public void showAppInfoDetailData(AppInfoDetail appInfoDetail) {
        List<String> imgs = Arrays.asList(appInfoDetail.getScreenshot().split(","));
        AppDetailPicAdapter adapter = new AppDetailPicAdapter(imgs);
        rvPic.setAdapter(adapter);
        rvPic.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL
                , false));
        rvPic.addItemDecoration(new SpaceItemDecoration(UiUtils.dp2px(3),
                SpaceItemDecoration.HORIZONTAL));

        expandableTextView.setText(appInfoDetail.getIntroduction());

        AppDetailAppAdapter sameAdapter = new AppDetailAppAdapter(appInfoDetail.getSameDevAppInfoList());
        rvSame.setAdapter(sameAdapter);
        rvSame.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL
                , false));
        rvSame.addItemDecoration(new SpaceItemDecoration(UiUtils.dp2px(0),
                SpaceItemDecoration.HORIZONTAL));

        AppDetailAppAdapter relateAdapter = new AppDetailAppAdapter(appInfoDetail.getRelateAppInfoList());
        rvRelate.setAdapter(relateAdapter);
        rvRelate.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL
                , false));
        rvRelate.addItemDecoration(new SpaceItemDecoration(UiUtils.dp2px(0),
                SpaceItemDecoration.HORIZONTAL));
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
                presenter.requestAppDetailData(id);
                break;
        }
    }

    @Override
    protected void retry() {
        super.retry();
        presenter.requestAppDetailData(id);
    }
}
