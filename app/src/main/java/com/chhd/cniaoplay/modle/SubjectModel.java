package com.chhd.cniaoplay.modle;

import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.Subject;
import com.chhd.cniaoplay.bean.SubjectDetail;

import io.reactivex.Observable;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by CWQ on 2017/6/14.
 */

public interface SubjectModel {

    Observable<BaseBean<PageBean<Subject>>> getSubjectData(int page);

    Observable<BaseBean<SubjectDetail>> getSubjectDetailData(int id);
}
