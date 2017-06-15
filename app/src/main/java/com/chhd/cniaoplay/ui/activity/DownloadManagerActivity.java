package com.chhd.cniaoplay.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.ui.base.SimpleActivity;
import com.chhd.cniaoplay.ui.fragment.download_manager.DownloadedFragment;
import com.chhd.cniaoplay.ui.fragment.download_manager.DownloadingFragment;
import com.chhd.cniaoplay.ui.fragment.download_manager.InstalledAppFragment;
import com.chhd.per_library.ui.base.BaseFmPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DownloadManagerActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private String[] titles = new String[]{"下载", "已完成", "已安装", "升级"};
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar();

        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
        fragments.add(DownloadingFragment.newInstance(titles[0]));
        fragments.add(DownloadedFragment.newInstance(titles[1]));
        fragments.add(InstalledAppFragment.newInstance(titles[2]));
        fragments.add(DownloadingFragment.newInstance(titles[3]));
        viewPager.setAdapter(new BaseFmPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setOffscreenPageLimit(titles.length - 1);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("下载管理");
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_download_manager;
    }
}
