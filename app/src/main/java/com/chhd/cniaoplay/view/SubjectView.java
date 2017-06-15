package com.chhd.cniaoplay.view;

import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.Subject;
import com.chhd.cniaoplay.view.base.BaseView;

/**
 * Created by CWQ on 2017/6/14.
 */

public interface SubjectView extends BaseView {

    void showData(PageBean<Subject> pageBean);
}
