package com.chhd.cniaoplay.ui.base;

import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.inject.component.AppComponent;
import com.chhd.cniaoplay.inject.component.DaggerAppInfoComponent;
import com.chhd.cniaoplay.inject.module.AppInfoModule;
import com.chhd.cniaoplay.inject.module.HttpModule;
import com.chhd.cniaoplay.presenter.AppInfoPresenter;
import com.chhd.cniaoplay.ui.view.AdapterLoadMoreView;
import com.chhd.cniaoplay.ui.adapter.AppAdatper;
import com.chhd.cniaoplay.view.AppInfoView;
import com.chhd.per_library.ui.decoration.SpaceItemDecoration;
import com.chhd.per_library.util.UiUtils;

import javax.inject.Inject;

/**
 * Created by CWQ on 2017/5/27.
 */

public abstract class SimpleAppInfoFragment extends SimpleMainFragment implements AppInfoView {

    @Inject
    protected AppInfoPresenter presenter;

    private AppAdatper adatper;
    protected int page = 0;

    @Override
    protected void initView() {
        super.initView();
        adatper = buildAdapter();
        if (adatper != null) {
            adatper.setLoadMoreView(new AdapterLoadMoreView());
            adatper.setOnLoadMoreListener(loadMoreListener, recyclerView);
            recyclerView.setAdapter(adatper);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.addItemDecoration(appSpaceItemDecoration);
        }
    }

    public abstract AppAdatper buildAdapter();

    private BaseQuickAdapter.RequestLoadMoreListener loadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {

        @Override
        public void onLoadMoreRequested() {
            presenter.requestRankData(page);
        }
    };

    @Override
    protected void lazyLoad() {
        super.lazyLoad();

        DaggerAppInfoComponent.builder()
                .appComponent(App.appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build().inject(this);
    }

    @Override
    public void onRefresh() {
        page = 0;
    }

    @Override
    public void showData(PageBean<AppInfo> pageBean) {
        if (pageBean.isHasMore()) {
            adatper.loadMoreComplete();
        } else {
            adatper.loadMoreEnd();
        }
        if (page == 0) {
            adatper.setNewData(pageBean.getDatas());
        } else {
            adatper.addData(pageBean.getDatas());
        }
        page++;
    }

    @Override
    protected void showErrorView() {
        if (adatper.getData().size() > 0) {
            showContentView();
            adatper.loadMoreEnd();
        } else {
            super.showErrorView();
        }
    }
}
