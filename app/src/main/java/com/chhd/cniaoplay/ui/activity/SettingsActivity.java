package com.chhd.cniaoplay.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.MessageEvent;
import com.chhd.cniaoplay.global.Action;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.ui.base.SimpleActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsActivity extends SimpleActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.switch_account)
    View switchAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.settings));
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onStart() {
        super.onStart();

        int visibility = App.user == null ? View.GONE : View.VISIBLE;
        switchAccount.setVisibility(visibility);
    }

    @OnClick({R.id.switch_account})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_account:
                App.clearLoginInfo();
                finish();
                EventBus.getDefault().post(new MessageEvent(Action.LOGOUT));
                break;
        }
    }

}
