package com.chhd.cniaoplay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.presenter.MainPresenterImpl;
import com.chhd.cniaoplay.ui.base.SimpleActivity;
import com.chhd.cniaoplay.ui.fragment.main.CategoryFragment;
import com.chhd.cniaoplay.ui.fragment.main.GameFragment;
import com.chhd.cniaoplay.ui.fragment.main.RankFragment;
import com.chhd.cniaoplay.ui.fragment.main.RecommendFragment;
import com.chhd.cniaoplay.ui.view.BadgeActionProvider;
import com.chhd.per_library.ui.base.BaseFmPagerAdapter;
import com.chhd.per_library.util.UiUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private String[] titles = new String[]{UiUtils.getString(R.string.recommend),
            UiUtils.getString(R.string.rank), UiUtils.getString(R.string.game), UiUtils.getString(R.string.category)};
    private List<Fragment> fragments = new ArrayList<>();
    private BadgeActionProvider downloadActionProvider;

    @Inject
    MainPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar();

        initView();


    }

    private void initView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);

        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }
        fragments.add(RecommendFragment.newInstance(titles[0]));
        fragments.add(RankFragment.newInstance(titles[1]));
        fragments.add(GameFragment.newInstance(titles[2]));
        fragments.add(CategoryFragment.newInstance(titles[3]));
        viewPager.setAdapter(new BaseFmPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setOffscreenPageLimit(titles.length - 1);
        tabLayout.setupWithViewPager(viewPager);

        navigationView.getHeaderView(0).findViewById(R.id.nav_header)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (App.user == null) {
                            Intent intent = new Intent(context, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            drawerLayout.closeDrawer(Gravity.START);
            switch (item.getItemId()) {
                case R.id.nav_settings: {
                    Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(intent);
                }
                break;
                case R.id.nav_download: {
                    Intent intent = new Intent(getApplicationContext(), DownloadManagerActivity.class);
                    startActivity(intent);
                }
                break;
                default:
                    Snackbar.make(toolbar, item.getTitle(), Snackbar.LENGTH_LONG).show();
                    break;
            }
            return false;
        }
    };

    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuItem item = menu.add(0, MENU_DEFAULT_ID, 0, R.string.search);
//        item.setIcon(R.drawable.ic_search_white_24dp);
//        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_download);
        downloadActionProvider = (BadgeActionProvider) MenuItemCompat.getActionProvider(menuItem);
        downloadActionProvider.setOnClickListener(0, new BadgeActionProvider.OnClickListener() {

            @Override
            public void onClick(int what) {
                Intent intent = new Intent(getApplicationContext(), DownloadManagerActivity.class);
                startActivity(intent);
            }
        });
        return true;
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (App.user != null) {
            TextView tvName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_name);
            tvName.setText(App.user.getUsername());
        } else {
            TextView tvName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_name);
            tvName.setText(getString(R.string.click_login));
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        downloadActionProvider.setIcon(R.drawable.ic_file_download_white_24dp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search: {
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
