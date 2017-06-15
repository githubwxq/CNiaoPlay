package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.SubjectDetail;
import com.chhd.cniaoplay.modle.SubjectModel;
import com.chhd.cniaoplay.rx.RxHelper;
import com.chhd.cniaoplay.rx.RxHttpReponseCompat;
import com.chhd.cniaoplay.rx.subscriber.SimpleSubscriber;
import com.chhd.cniaoplay.util.LoggerUtils;
import com.chhd.cniaoplay.view.SubjectDetailView;
import com.chhd.cniaoplay.view.SubjectView;

import javax.inject.Inject;

/**
 * Created by CWQ on 2017/6/14.
 */

public class SubjectDetailPresenterImpl implements SubjectDetailPresenter {

    private SubjectDetailView view;
    private SubjectModel model;

    @Inject
    public SubjectDetailPresenterImpl(SubjectDetailView view, SubjectModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestSubjectDetailData(int id) {
        model
                .getSubjectDetailData(id)
                .compose(RxHttpReponseCompat.<SubjectDetail>compatResult())
                .subscribe(new SimpleSubscriber<SubjectDetail>() {

                    @Override
                    public void success(SubjectDetail subjectDetail) {
                        view.showData(subjectDetail);
                    }
                });
    }
}
