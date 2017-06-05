package com.chhd.cniaoplay.view;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.RecommendBean;
import com.chhd.cniaoplay.view.base.BaseView;

import java.util.List;

/**
 * Created by CWQ on 2017/5/28.
 */

public interface RecommendView extends BaseView {

    void showData(List<AppInfo> list);

    void showRecommendData(RecommendBean bean);

}
