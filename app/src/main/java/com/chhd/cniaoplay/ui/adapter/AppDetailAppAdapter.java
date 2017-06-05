package com.chhd.cniaoplay.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.ui.base.SimpleAdapter;

import java.util.List;

/**
 * Created by CWQ on 2017/6/5.
 */

public class AppDetailAppAdapter extends SimpleAdapter<AppInfo, BaseViewHolder> {

    public AppDetailAppAdapter(@Nullable List<AppInfo> data) {
        super(R.layout.item_list_app_detail_app, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        helper.setText(R.id.tv_name, item.getDisplayName());

        ImageView ivIcon = helper.getView(R.id.iv_icon);
        Glide.with(mContext).load(ApiService.BASE_IMG_URL + item.getIcon()).into(ivIcon);
    }
}
