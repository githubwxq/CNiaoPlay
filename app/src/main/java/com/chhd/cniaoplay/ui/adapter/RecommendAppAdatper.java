package com.chhd.cniaoplay.ui.adapter;

import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.ui.base.SimpleAdapter;
import com.chhd.per_library.ui.base.BaseHolder;
import com.chhd.per_library.util.ToastUtil;

import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by CWQ on 2017/5/8.
 */

public class RecommendAppAdatper extends SimpleAdapter<AppInfo, BaseViewHolder> {

    private String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    public RecommendAppAdatper(@Nullable List<AppInfo> data) {
        super(R.layout.item_list_recomend_app, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final AppInfo item) {
        Holder holder = new Holder(helper.itemView);
        Glide.with(mContext).load(baseImgUrl + item.getIcon()).into(holder.ivIcon);
        holder.tvAppName.setText(item.getDisplayName());
        holder.tvPublisherName.setText(item.getPublisherName());
        holder.tvSize.setText(Formatter.formatFileSize(mContext, item.getApkSize()));
    }

    static class Holder extends BaseHolder {

        @ViewInject(R.id.iv_icon)
        ImageView ivIcon;
        @ViewInject(R.id.tv_app_name)
        TextView tvAppName;
        @ViewInject(R.id.tv_publisher_name)
        TextView tvPublisherName;
        @ViewInject(R.id.tv_size)
        TextView tvSize;

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
