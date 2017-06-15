package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.SearchResult;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.base.BaseModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by CWQ on 2017/6/15.
 */

public class SearchModelImpl extends BaseModel implements SearchModel {

    public SearchModelImpl(ApiService apiService) {
        super(apiService);
    }

    @Override
    public Observable<BaseBean<List<String>>> getSearchSuggestData(String keyword) {
        return apiService.getSearchSuggestData(keyword);
    }

    @Override
    public Observable<BaseBean<SearchResult>> getSearchResultData(String keyword) {
        return apiService.getSearchResultData(keyword);
    }
}
