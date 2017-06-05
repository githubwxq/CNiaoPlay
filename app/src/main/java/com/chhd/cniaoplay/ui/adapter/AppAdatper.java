package com.chhd.cniaoplay.ui.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.ui.activity.AppDetailActivity;
import com.chhd.cniaoplay.ui.base.SimpleAdapter;
import com.chhd.per_library.ui.base.BaseHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CWQ on 2017/5/8.
 */

public class AppAdatper extends SimpleAdapter<AppInfo, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    private String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    private Builder builder;

    public static Builder builder() {
        return new Builder();
    }

    public AppAdatper(List<AppInfo> data) {
        super(R.layout.item_list_app, data);

        setOnItemClickListener(this);
    }

    public AppAdatper(Builder builder) {
        super(R.layout.item_list_app, new ArrayList<AppInfo>());
        this.builder = builder;

        setOnItemClickListener(this);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        Holder holder = new Holder(helper.itemView);
        Glide.with(mContext).load(baseImgUrl + item.getIcon()).into(holder.ivIcon);
        holder.tvAppName.setText(item.getDisplayName());
        holder.tvPublisherName.setText(item.getPublisherName());
        holder.tvSize.setText(Formatter.formatFileSize(mContext, item.getApkSize()));
        int v = builder == null || builder.isShowPosition == false ? View.GONE : View.VISIBLE;
        holder.tvPos.setVisibility(v);
        holder.tvPos.setText(item.getPosition() + 1 + ".");

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Activity activity = ((Activity) mContext);
        Intent intent = new Intent(activity, AppDetailActivity.class);
        intent.putExtra("appInfo", getData().get(position));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImageView ivIcon = ButterKnife.findById(view, R.id.iv_icon);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, ivIcon, ivIcon.getTransitionName());
            activity.startActivityForResult(intent, 0, options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    static class Holder extends BaseHolder {

        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_app_name)
        TextView tvAppName;
        @BindView(R.id.tv_publisher_name)
        TextView tvPublisherName;
        @BindView(R.id.tv_size)
        TextView tvSize;
        @BindView(R.id.tv_pos)
        TextView tvPos;

        public Holder(View itemView) {
            super(itemView);
        }
    }

    public static class Builder {

        private boolean isShowPosition;

        public Builder showPosition(boolean b) {
            isShowPosition = b;
            return this;
        }

        public AppAdatper build() {
            return new AppAdatper(this);
        }
    }

}
