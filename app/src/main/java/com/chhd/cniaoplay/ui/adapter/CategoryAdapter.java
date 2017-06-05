package com.chhd.cniaoplay.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.Category;
import com.chhd.cniaoplay.http.ApiService;
import com.chhd.cniaoplay.ui.base.SimpleAdapter;
import com.chhd.per_library.ui.base.BaseHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by CWQ on 2017/6/1.
 */

public class CategoryAdapter extends SimpleAdapter<Category, BaseViewHolder> {

    public CategoryAdapter(List<Category> categories) {
        super(R.layout.item_grid_category, new ArrayList<Category>());
    }

    @Override
    protected void convert(BaseViewHolder helper, Category item) {
        Holder holder = new Holder(helper.itemView);
        Glide.with(mContext).load(ApiService.BASE_IMG_URL + item.getIcon()).into(holder.ivIcon);
        holder.tvTitle.setText(item.getName());
    }

    static class Holder extends BaseHolder {

        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
