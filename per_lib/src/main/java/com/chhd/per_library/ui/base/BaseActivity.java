package com.chhd.per_library.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CWQ on 2017/5/4.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected final int MENU_DEFAULT_ID = 10;
    protected Activity context;

    public static List<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());

        x.view().inject(this);

        context = this;

        activities.add(this);
    }

    public abstract int getLayoutResID();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        activities.remove(this);
    }
}
