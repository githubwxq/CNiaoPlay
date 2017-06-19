package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.Category;
import com.chhd.cniaoplay.modle.CategoryModel;
import com.chhd.cniaoplay.http.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.http.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.view.CategoryView;

import java.util.List;

/**
 * Created by CWQ on 2017/6/1.
 */

public class CategoryPresenterImpl implements CategoryPresenter {

    private CategoryView view;
    private CategoryModel model;

    public CategoryPresenterImpl(CategoryView view, CategoryModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestCategoryData() {
        model.getCategoryData()
                .compose(RxHttpReponseCompat.<List<Category>>compatResult())
                .subscribe(new SimpleSubscriber<List<Category>>(view) {

                    @Override
                    public void success(List<Category> categories) {
                        view.showData(categories);
                    }
                });
    }
}
