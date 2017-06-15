package com.chhd.cniaoplay.ui.activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.inject.component.DaggerSearchComponent;
import com.chhd.cniaoplay.inject.module.SearchModule;
import com.chhd.cniaoplay.presenter.SearchPresenterImpl;
import com.chhd.cniaoplay.ui.base.SimpleActivity;
import com.chhd.per_library.util.UiUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lankton.flowlayout.FlowLayout;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class SearchActivity extends SimpleActivity implements com.chhd.cniaoplay.view.SearchView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.flow_layout)
    FlowLayout flowLayout;

    private String[] texts = new String[]{"QQ", "微信", "今日头条", "哔哩哔哩", "爱奇艺", "微博"
            , "手机京东", "闲鱼", "支付宝"};

    @Inject
    SearchPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar();

        for (int i = 0; i < texts.length; i++) {
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, UiUtils.dp2px(10), 0);
            TextView textView = (TextView) View.inflate(this, R.layout.text_view, null);
            textView.setText(texts[i]);
            flowLayout.addView(textView, lp);
        }

        DaggerSearchComponent.builder()
                .appComponent(App.appComponent)
                .searchModule(new SearchModule(this))
                .build().inject(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.search);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_search;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        TextView textBadge = ButterKnife.findById(searchView, R.id.search_badge);
        textBadge.setText("|");
        textBadge.setVisibility(View.VISIBLE);

        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        SearchView.SearchAutoComplete search = ButterKnife.findById(searchView, R.id.search_src_text);

        RxTextView
                .textChanges(search)
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter(new Predicate<CharSequence>() {

                    @Override
                    public boolean test(@NonNull CharSequence charSequence) throws Exception {
                        return charSequence.toString().trim().length() > 0;
                    }
                })
                .subscribe(new Consumer<CharSequence>() {

                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        presenter.requestSearchSuggestData(charSequence.toString());
                    }
                });

        return super.onCreateOptionsMenu(menu);
    }
}
