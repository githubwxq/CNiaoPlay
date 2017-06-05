package com.chhd.cniaoplay.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.ui.AppBarStateChangeListener;
import com.chhd.cniaoplay.ui.base.SimpleActivity;
import com.chhd.cniaoplay.ui.fragment.AppDetailFragment;
import com.chhd.cniaoplay.util.LoggerUtils;

import butterknife.BindView;

public class AppDetailActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_app_name)
    TextView tvAppName;
    @BindView(R.id.tv_publisher_name)
    TextView tvPublisherName;
    @BindView(R.id.tv_size)
    TextView tvSize;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;

    private AppInfo appInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appInfo = getIntent().getParcelableExtra("appInfo");

        initActionBar();

        initView();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, AppDetailFragment.newInstance(appInfo.getId()));
        transaction.commit();

    }

    private void initView() {
        Glide.with(this).load(ApiService.BASE_IMG_URL + appInfo.getIcon()).into(ivIcon);
        tvAppName.setText(appInfo.getDisplayName());
        tvPublisherName.setText(appInfo.getPublisherName());
        tvSize.setText(Formatter.formatFileSize(this, appInfo.getApkSize()));

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {

            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    getSupportActionBar().setTitle(appInfo.getDisplayName());
                } else {
                    getSupportActionBar().setTitle("");
                }
            }
        });
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_app_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(0, MENU_DEFAULT_ID, 0, R.string.search);
        item.setIcon(R.drawable.ic_search_white_24dp);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
}
