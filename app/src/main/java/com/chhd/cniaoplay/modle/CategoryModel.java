package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.Category;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by CWQ on 2017/6/1.
 */

public interface CategoryModel {

    Observable<BaseBean<List<Category>>> getCategoryData();
}
