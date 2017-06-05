package com.chhd.cniaoplay.ui.activity;

import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.Category;
import com.chhd.cniaoplay.ui.base.SimpleActivity;
import com.chhd.cniaoplay.ui.fragment.CategoryAppFragment;
import com.chhd.per_library.ui.base.BaseFmPagerAdapter;
import com.chhd.per_library.util.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryAppActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private String[] titles = new String[]{UiUtils.getString(R.string.competitive_product),
            UiUtils.getString(R.string.rank), UiUtils.getString(R.string.new_product)};
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar();

        Category category = getIntent().getParcelableExtra("category");

        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
            fragments.add(CategoryAppFragment.newInstance(titles[i], category.getId()));
        }
        viewPager.setAdapter(new BaseFmPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setOffscreenPageLimit(titles.length - 1);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initActionBar() {
        Category category = getIntent().getParcelableExtra("category");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(category.getName());
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_category_app;
    }
}
