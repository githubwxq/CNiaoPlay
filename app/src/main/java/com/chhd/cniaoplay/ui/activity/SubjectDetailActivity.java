package com.chhd.cniaoplay.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.Subject;
import com.chhd.cniaoplay.bean.SubjectDetail;
import com.chhd.cniaoplay.global.App;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.inject.component.DaggerSubjectDetailComponent;
import com.chhd.cniaoplay.inject.module.SubjectDetailModule;
import com.chhd.cniaoplay.presenter.SubjectDetailPresenterImpl;
import com.chhd.cniaoplay.ui.adapter.AppAdatper;
import com.chhd.cniaoplay.ui.base.ProgressActivity;
import com.chhd.cniaoplay.util.LoggerUtils;
import com.chhd.cniaoplay.view.SubjectDetailView;
import com.chhd.per_library.ui.decoration.SpaceItemDecoration;
import com.chhd.per_library.util.UiUtils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SubjectDetailActivity extends ProgressActivity implements SubjectDetailView {

    @Inject
    SubjectDetailPresenterImpl presenter;

    private ImageView ivPic;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    public void findViewById() {
        toolbar = ButterKnife.findById(contentView, R.id.toolbar);
        ivPic = ButterKnife.findById(contentView, R.id.iv_pic);
        recyclerView = ButterKnife.findById(contentView, R.id.recycler_view);
    }

    @Override
    public int getContentResId() {
        return R.layout.activity_subject_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Subject subject = getIntent().getParcelableExtra("subject");

        DaggerSubjectDetailComponent.builder()
                .appComponent(App.appComponent)
                .subjectDetailModule(new SubjectDetailModule(this))
                .build().inject(this);

        presenter.requestSubjectDetailData(subject.getRelatedId());

        initActionBar();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public void showData(SubjectDetail subjectDetail) {
        AppAdatper adatper = AppAdatper.builder().build();
        adatper.setNewData(subjectDetail.getListApp());
        recyclerView.setAdapter(adatper);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(spaceItemDecoration);
    }

    private SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(UiUtils.dp2px(SPACE_FOR_APP),
            SpaceItemDecoration.VERTICAL, true);
}
