package com.chhd.cniaoplay.view;

import com.chhd.cniaoplay.bean.Category;
import com.chhd.cniaoplay.view.base.BaseView;

import java.util.List;

/**
 * Created by CWQ on 2017/6/1.
 */

public interface CategoryView extends BaseView {

    void showData(List<Category> categories);
}
