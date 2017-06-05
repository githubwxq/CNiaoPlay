package com.chhd.cniaoplay.presenter;

/**
 * Created by CWQ on 2017/5/28.
 */

public interface AppInfoPresenter {

    void requestRankData(int page);

    void requestGameData(int page);

    void requestAppDataByCategory(int type, int categoryId, int page);

}
