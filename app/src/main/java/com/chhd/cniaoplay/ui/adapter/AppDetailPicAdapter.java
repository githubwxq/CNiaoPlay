package com.chhd.cniaoplay.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.ui.base.SimpleAdapter;

import java.util.List;

/**
 * Created by CWQ on 2017/6/4.
 */

public class AppDetailPicAdapter extends SimpleAdapter<String, BaseViewHolder> {

    public AppDetailPicAdapter(@Nullable List<String> data) {
        super(R.layout.item_list_app_detail_pic, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView ivPic = helper.getView(R.id.iv_pic);
        Glide.with(mContext).load(ApiService.BASE_IMG_URL + item).into(ivPic);
    }
}
