package com.chhd.cniaoplay.presenter.contract;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.presenter.BasePresenter;
import com.chhd.cniaoplay.ui.BaseView;

import java.util.List;

/**
 * Created by CWQ on 2017/5/9.
 */

public interface RecommendContract {

    interface View extends BaseView {
        void showResult(List<AppInfo> list);
    }

    interface Presenter extends BasePresenter {
        void requestData();
    }
}
