package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.Subject;
import com.chhd.cniaoplay.bean.SubjectDetail;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.modle.base.BaseModel;

import io.reactivex.Observable;

/**
 * Created by CWQ on 2017/6/14.
 */

public class SubjectModelImpl extends BaseModel implements SubjectModel {

    public SubjectModelImpl(ApiService apiService) {
        super(apiService);
    }

    @Override
    public Observable<BaseBean<PageBean<Subject>>> getSubjectData(int page) {
        return apiService.getSubjectData(page);
    }

    @Override
    public Observable<BaseBean<SubjectDetail>> getSubjectDetailData(int id) {
        return apiService.getSubjectDetailData(id);
    }
}
