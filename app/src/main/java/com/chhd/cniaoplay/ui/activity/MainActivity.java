package com.chhd.cniaoplay.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.ui.base.SimpleActivity;
import com.chhd.cniaoplay.ui.fragment.CategoryFragment;
import com.chhd.cniaoplay.ui.fragment.RankFragment;
import com.chhd.cniaoplay.ui.fragment.RecommendFragment;
import com.chhd.per_library.ui.base.BaseFmPagerAdapter;
import com.chhd.per_library.util.UiUtil;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SimpleActivity {

    @ViewInject(R.id.toolbar)
    Toolbar toolbar;
    @ViewInject(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @ViewInject(R.id.navigation_view)
    NavigationView navigationView;
    @ViewInject(R.id.tab_layout)
    TabLayout tabLayout;
    @ViewInject(R.id.view_pager)
    ViewPager viewPager;

    private String[] titles = new String[]{UiUtil.getString(R.string.recommend),
            UiUtil.getString(R.string.rank), UiUtil.getString(R.string.category)};
    private List<Fragment> fragments = new ArrayList<>();

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
        fragments.add(CategoryFragment.newInstance(titles[2]));
        viewPager.setAdapter(new BaseFmPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setOffscreenPageLimit(titles.length - 1);
        tabLayout.setupWithViewPager(viewPager);
    }

    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            drawerLayout.closeDrawer(Gravity.START);
            Snackbar.make(toolbar, item.getTitle(), Snackbar.LENGTH_LONG).show();
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
        MenuItem item = menu.add(0, MENU_DEFAULT_ID, 0, "搜索");
        item.setIcon(R.drawable.ic_search_white_24dp);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }
}
