package com.chhd.cniaoplay.presenter;

/**
 * Created by CWQ on 2017/6/15.
 */

public interface SearchPresenter {

    void requestSearchSuggestData(String keyword);

    void requestSearchResultData(String keyword);
}
