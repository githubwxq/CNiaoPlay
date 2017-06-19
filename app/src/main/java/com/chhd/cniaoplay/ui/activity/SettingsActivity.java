package com.chhd.cniaoplay.ui.activity;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.MessageEvent;
import com.chhd.cniaoplay.global.Action;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.ui.base.SimpleActivity;
import com.chhd.per_library.ui.base.AppCompatPreferenceActivity;
import com.chhd.per_library.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatPreferenceActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        addPreferencesFromResource(R.xml.pref_settings);

        ButterKnife.bind(this);

        initActionBar();

        Preference preference = getPreferenceManager().findPreference("switch_account");
        preference.setOnPreferenceClickListener(onPreferenceClickListener);
    }

    private Preference.OnPreferenceClickListener onPreferenceClickListener = new Preference
            .OnPreferenceClickListener() {

        @Override
        public boolean onPreferenceClick(Preference preference) {
            switch (preference.getKey()) {
                case "switch_account":
                    App.clearLoginInfo();
                    finish();
                    EventBus.getDefault().post(new MessageEvent(Action.LOGOUT));
                    break;
            }
            return false;
        }
    };

    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.settings));
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
