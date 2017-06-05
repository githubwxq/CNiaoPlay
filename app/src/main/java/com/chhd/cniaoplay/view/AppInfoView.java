package com.chhd.cniaoplay.view;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.view.base.BaseView;

/**
 * Created by CWQ on 2017/5/28.
 */

public interface AppInfoView extends BaseView {

    void showData(PageBean<AppInfo> pageBean);
}
