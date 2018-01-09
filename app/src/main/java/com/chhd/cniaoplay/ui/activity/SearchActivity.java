package com.chhd.cniaoplay.ui.activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.SearchResult;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.inject.component.DaggerSearchComponent;
import com.chhd.cniaoplay.inject.module.SearchModule;
import com.chhd.cniaoplay.presenter.SearchPresenterImpl;
import com.chhd.cniaoplay.ui.adapter.AppAdatper;
import com.chhd.cniaoplay.ui.adapter.SearchSuggestAdapter;
import com.chhd.cniaoplay.ui.base.SimpleActivity;
import com.chhd.per_library.util.SpUtils;
import com.chhd.per_library.util.UiUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lankton.flowlayout.FlowLayout;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class SearchActivity extends SimpleActivity implements com.chhd.cniaoplay.view.SearchView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.home)
    View home;
    @BindView(R.id.fl_recommend)
    FlowLayout flRecommend;
    @BindView(R.id.fl_history)
    FlowLayout flHistory;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;

    private String[] texts = new String[]{"QQ", "微信", "今日头条", "哔哩哔哩", "爱奇艺", "微博"
            , "手机京东", "闲鱼", "支付宝"};

    @Inject
    SearchPresenterImpl presenter;

    private SearchView.SearchAutoComplete search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar();

        for (int i = 0; i < texts.length; i++) {
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams
                    (ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, UiUtils.dp2px(10), 0);
            TextView textView = (TextView) View.inflate(this, R.layout.text_view, null);
            textView.setText(texts[i]);
            textView.setTag(texts[i]);
            textView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String tag = (String) v.getTag();
                    search.setText(tag);
                    search.setSelection(tag.length());
                }
            });
            flRecommend.addView(textView, lp);
        }

        DaggerSearchComponent.builder()
                .appComponent(App.appComponent)
                .searchModule(new SearchModule(this))
                .build().inject(this);

        showSearchHistory();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.search);

        SearchView searchView;
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

        searchView.setIconifiedByDefault(false);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        search = ButterKnife.findById(searchView, R.id.search_src_text);

        RxTextView
                .textChanges(search)
                .debounce(100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<CharSequence>() {

                    @Override
                    public boolean test(@NonNull CharSequence charSequence) throws Exception {
                        if (charSequence.toString().trim().length() > 0) {
                        } else {
                            showSearchHistory();
                            home.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.INVISIBLE);
                        }
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

    @OnClick({R.id.iv_delete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_delete:
                SpUtils.putString("searchHistory", "");
                showSearchHistory();
                break;
        }
    }

    public void showSearchHistory() {
        String[] histories = SpUtils.getString("searchHistory").split(",");

        flHistory.removeAllViews();

        for (int i = 0; i < histories.length; i++) {
            if (!TextUtils.isEmpty(histories[i])) {
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams
                        (ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 0, UiUtils.dp2px(10), 0);
                TextView textView = (TextView) View.inflate(this, R.layout.text_view, null);
                textView.setText(histories[i]);
                textView.setTag(histories[i]);
                textView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String tag = (String) v.getTag();
                        search.setText(tag);
                        search.setSelection(tag.length());
                    }
                });
                flHistory.addView(textView, lp);
            }
        }
    }

    @Override
    public void showSearchSuggestData(List<String> strings) {
        home.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        SearchSuggestAdapter adatper = new SearchSuggestAdapter();
        adatper.setNewData(strings);
        adatper.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(adatper);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showSearchResultData(SearchResult searchResult) {
        home.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        AppAdatper adatper = AppAdatper.builder().build();
        adatper.setNewData(searchResult.getListApp());
        recyclerView.setAdapter(adatper);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.removeItemDecoration(appSpaceItemDecoration);
        recyclerView.addItemDecoration(appSpaceItemDecoration);
    }


    private BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter
            .OnItemClickListener() {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if (adapter instanceof SearchSuggestAdapter) {
                SearchSuggestAdapter searchSuggestAdapter = (SearchSuggestAdapter) adapter;
                presenter.requestSearchResultData(searchSuggestAdapter.getItem(position));
            }
        }
    };


}
