package com.chhd.cniaoplay.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.inject.component.DaggerLoginComponent;
import com.chhd.cniaoplay.inject.module.HttpModule;
import com.chhd.cniaoplay.inject.module.LoginModule;
import com.chhd.cniaoplay.presenter.LoginPresenter;
import com.chhd.cniaoplay.ui.base.SimpleActivity;
import com.chhd.cniaoplay.view.LoginView;
import com.chhd.per_library.util.AppUtils;
import com.chhd.per_library.util.SpUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

public class LoginActivity extends SimpleActivity implements LoginView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_number)
    TextInputLayout textNumber;
    @BindView(R.id.text_password)
    TextInputLayout textPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar();

        initView();

        ButterKnife.bind(this);

        DaggerLoginComponent.builder()
                .httpModule(new HttpModule())
                .loginModule(new LoginModule(this))
                .build().inject(this);
    }

    private void initView() {

        new RxPermissions(this).request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            String number = AppUtils.getMobliePhoneNumber();
                            textNumber.getEditText().setText(number);
                        }
                    }
                });

        String account = SpUtils.getString("account");
        if (!TextUtils.isEmpty(account)) {
            textNumber.getEditText().setText(account);
        }

        textPassword.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                attemptLogin();
                return true;
            }
        });

        Observable<CharSequence> obNum = RxTextView.textChanges(textNumber.getEditText());
        Observable<CharSequence> obPwd = RxTextView.textChanges(textPassword.getEditText());
        Observable.combineLatest(obNum, obPwd, new Func2<CharSequence, CharSequence, Boolean>() {

            @Override
            public Boolean call(CharSequence num, CharSequence pwd) {
                return num.toString().trim().length() > 0 && pwd.toString().trim().length() > 0;
            }
        }).subscribe(new Action1<Boolean>() {

            @Override
            public void call(Boolean aBoolean) {
                RxView.enabled(btnLogin).call(aBoolean);
            }
        });
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.user_login);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_login;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showSuccess() {
        finish();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void after() {

    }

    @OnClick({R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                attemptLogin();
                break;
        }
    }

    private void attemptLogin() {
        String num = textNumber.getEditText().getText().toString();
        String pwd = textPassword.getEditText().getText().toString();
        if (TextUtils.isEmpty(num)) {
            textNumber.getEditText().setError(getString(R.string.please_input_moblie_phone_number));
            textNumber.getEditText().requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            textPassword.getEditText().setError(getString(R.string.please_input_password));
            textPassword.getEditText().requestFocus();
            return;
        }
        SpUtils.putString("account", num);
        presenter.requestLogin(num, pwd);
    }
}

