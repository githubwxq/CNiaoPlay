package com.chhd.cniaoplay.ui.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chhd.cniaoplay.bean.Category;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.inject.component.DaggerCategoryComponent;
import com.chhd.cniaoplay.inject.module.CategoryModule;
import com.chhd.cniaoplay.inject.module.HttpModule;
import com.chhd.cniaoplay.presenter.CategoryPresenter;
import com.chhd.cniaoplay.ui.activity.CategoryAppActivity;
import com.chhd.cniaoplay.ui.adapter.CategoryAdapter;
import com.chhd.cniaoplay.ui.base.SimpleMainFragment;
import com.chhd.cniaoplay.view.CategoryView;
import com.chhd.per_library.ui.decoration.GridSpaceItemDecoration;
import com.chhd.per_library.util.UiUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CategoryFragment extends SimpleMainFragment implements CategoryView {

    @Inject
    CategoryPresenter presenter;
    private CategoryAdapter adapter;

    public CategoryFragment() {
    }

    public static CategoryFragment newInstance(String title) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();

        adapter = new CategoryAdapter(new ArrayList<Category>());
        adapter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.addItemDecoration(new GridSpaceItemDecoration(3, UiUtils.dp2px(10), true));

        DaggerCategoryComponent.builder()
                .appComponent(App.appComponent)
                .categoryModule(new CategoryModule(this))
                .build().inject(this);
    }

    private BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(getActivity(), CategoryAppActivity.class);
            intent.putExtra("category", CategoryFragment.this.adapter.getData().get(position));
            startActivity(intent);
        }
    };

    @Override
    public void onRefresh() {
        presenter.requestCategoryData();
    }

    @Override
    public void showData(List<Category> categories) {
        adapter.setNewData(categories);
    }
}
