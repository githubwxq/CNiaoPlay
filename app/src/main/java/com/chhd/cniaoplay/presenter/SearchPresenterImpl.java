package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.SearchResult;
import com.chhd.cniaoplay.modle.SearchModel;
import com.chhd.cniaoplay.http.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.http.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.view.SearchView;
import com.chhd.per_library.util.SpUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by CWQ on 2017/6/15.
 */

public class SearchPresenterImpl implements SearchPresenter {

    private SearchView view;
    private SearchModel model;

    @Inject
    public SearchPresenterImpl(SearchView view, SearchModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestSearchSuggestData(String keyword) {
        model
                .getSearchSuggestData(keyword)
                .compose(RxHttpReponseCompat.<List<String>>compatResult())
                .subscribe(new SimpleSubscriber<List<String>>() {

                    @Override
                    public void success(List<String> strings) {
                        view.showSearchSuggestData(strings);
                    }
                });
    }

    @Override
    public void requestSearchResultData(String keyword) {
        saveSearchHistory(keyword);

        model
                .getSearchResultData(keyword)
                .compose(RxHttpReponseCompat.<SearchResult>compatResult())
                .subscribe(new SimpleSubscriber<SearchResult>() {

                    @Override
                    public void success(SearchResult searchResult) {
                        view.showSearchResultData(searchResult);
                    }
                });
    }

    private void saveSearchHistory(String keyword) {
        String history = SpUtils.getString("searchHistory");
        if (history.length() > 0) {
            history = history + "," + keyword;
        } else {
            history = keyword;
        }
        SpUtils.putString("searchHistory", history);
    }

}
