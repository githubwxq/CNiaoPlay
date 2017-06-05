package com.chhd.per_library.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by CWQ on 2017/5/4.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected final int MENU_DEFAULT_ID = 10;
    protected Activity context;

    public static List<Activity> activities = new ArrayList<>();
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());

        bind = ButterKnife.bind(this);

        context = this;

        activities.add(this);
    }

    public abstract int getLayoutResID();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        activities.remove(this);

        bind.unbind();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
