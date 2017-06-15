package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.Category;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.base.BaseModel;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by CWQ on 2017/6/1.
 */

public class CategoryModelImpl extends BaseModel implements CategoryModel {

    public CategoryModelImpl(ApiService apiService) {
        super(apiService);
    }

    @Override
    public Observable<BaseBean<List<Category>>> getCategoryData() {
        return apiService.getCategoryData();
    }
}
