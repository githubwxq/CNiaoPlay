package com.chhd.cniaoplay.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.Subject;
import com.chhd.cniaoplay.http.ApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CWQ on 2017/6/14.
 */

public class SubjectAdapter extends BaseQuickAdapter<Subject, BaseViewHolder> {

    public SubjectAdapter() {
        super(R.layout.item_list_subject, new ArrayList<Subject>());
    }

    @Override
    protected void convert(BaseViewHolder helper, Subject item) {
        ImageView ivPic = helper.getView(R.id.iv_pic);
        Glide.with(mContext).load(ApiService.BASE_IMG_URL + item.getMticon()).into(ivPic);
    }
}
