package com.chhd.cniaoplay.view;

import com.chhd.cniaoplay.bean.SearchResult;

import java.util.List;

/**
 * Created by CWQ on 2017/6/15.
 */

public interface SearchView {

    void showSearchSuggestData(List<String> strings);

    void showSearchResultData(SearchResult searchResult);
}
