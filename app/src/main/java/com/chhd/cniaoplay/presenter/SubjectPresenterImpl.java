package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.Subject;
import com.chhd.cniaoplay.modle.SubjectModel;
import com.chhd.cniaoplay.http.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.http.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.view.SubjectView;

import javax.inject.Inject;

/**
 * Created by CWQ on 2017/6/14.
 */

public class SubjectPresenterImpl implements SubjectPresenter {

    private SubjectView view;
    private SubjectModel model;

    @Inject
    public SubjectPresenterImpl(SubjectView view, SubjectModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestSubjectData(int page) {
        model
                .getSubjectData(page)
                .compose(RxHttpReponseCompat.<PageBean<Subject>>compatResult())
                .subscribe(new SimpleSubscriber<PageBean<Subject>>(view) {

                    @Override
                    public void success(PageBean<Subject> pageBean) {
                        view.showData(pageBean);
                    }
                });

    }

}
