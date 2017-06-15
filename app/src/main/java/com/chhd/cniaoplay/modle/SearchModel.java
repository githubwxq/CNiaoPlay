package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.SearchResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by CWQ on 2017/6/15.
 */

public interface SearchModel {

    Observable<BaseBean<List<String>>> getSearchSuggestData(String keyword);

    Observable<BaseBean<SearchResult>> getSearchResultData(String keyword);
}
