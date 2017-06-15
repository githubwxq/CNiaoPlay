package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.SearchResult;
import com.chhd.cniaoplay.modle.SearchModel;
import com.chhd.cniaoplay.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.rx.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.util.LoggerUtils;
import com.chhd.cniaoplay.view.SearchView;

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
                        LoggerUtils.i("strings: " + strings);
                    }
                });
    }

    @Override
    public void requestSearchResultData(String keyword) {
        model
                .getSearchResultData(keyword)
                .compose(RxHttpReponseCompat.<SearchResult>compatResult())
                .subscribe(new SimpleSubscriber<SearchResult>() {

                    @Override
                    public void success(SearchResult searchResult) {
                        LoggerUtils.i("size: " + searchResult.getListApp().size());
                    }
                });
    }
}
