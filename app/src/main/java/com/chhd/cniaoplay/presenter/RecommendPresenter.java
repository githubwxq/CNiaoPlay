package com.chhd.cniaoplay.presenter;

import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.Page;
import com.chhd.cniaoplay.data.RecommendModel;
import com.chhd.cniaoplay.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by CWQ on 2017/5/9.
 */

public class RecommendPresenter implements RecommendContract.Presenter {

    private RecommendContract.View view;
    private RecommendModel model;

    public RecommendPresenter(RecommendContract.View view, RecommendModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void requestData() {
        model.getData(new Callback<Page<AppInfo>>() {

            @Override
            public void onResponse(Call<Page<AppInfo>> call, Response<Page<AppInfo>> response) {
                view.showResult(response.body().getDatas());
            }

            @Override
            public void onFailure(Call<Page<AppInfo>> call, Throwable t) {

            }
        });
    }
}
