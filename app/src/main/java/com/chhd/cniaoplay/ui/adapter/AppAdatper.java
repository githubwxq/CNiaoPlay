package com.chhd.cniaoplay.ui.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.PopupMenu;
import android.text.format.Formatter;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chhd.cniaoplay.ui.DownloadController;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.ui.activity.AppDetailActivity;
import com.chhd.cniaoplay.ui.base.SimpleAdapter;
import com.chhd.cniaoplay.util.LoggerUtils;
import com.chhd.per_library.ui.base.BaseHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Action;
import zlc.season.rxdownload2.RxDownload;

/**
 * Created by CWQ on 2017/5/8.
 */

public class AppAdatper extends SimpleAdapter<AppInfo, BaseViewHolder> implements
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    private String baseImgUrl = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    private Builder builder;

    public static Builder builder() {
        return new Builder();
    }

    public AppAdatper(Builder builder) {
        super(R.layout.item_list_app, new ArrayList<AppInfo>());
        this.builder = builder;

        setOnItemClickListener(this);

        setOnItemChildClickListener(this);
    }

    @Override
    protected void convert(final BaseViewHolder helper, AppInfo item) {
        final Holder holder = new Holder(helper.itemView);
        Glide.with(mContext).load(baseImgUrl + item.getIcon()).into(holder.ivIcon);
        holder.tvAppName.setText(item.getDisplayName());
        holder.tvPublisherName.setText(item.getPublisherName());
        holder.tvSize.setText(Formatter.formatFileSize(mContext, item.getApkSize()));
        int v = builder == null || builder.isShowPosition == false ? View.GONE : View.VISIBLE;
        holder.tvPos.setVisibility(v);
        v = builder == null || builder.isShowMore == false ? View.GONE : View.VISIBLE;
        holder.ivMore.setVisibility(v);
        holder.tvPos.setText(item.getPosition() + 1 + ".");

        helper.addOnClickListener(R.id.iv_more);

        holder.btn.setTag(item);

//        if (helper.getPosition() == 0 || helper.getPosition() == 1 || helper.getPosition() == 2
//                || helper.getPosition() == 3 || helper.getPosition() == 4) {
//            DownloadController controller = new DownloadController();
//            controller.bindData(holder.btn, item);
//        } else {
//            holder.btn.setText(mContext.getString(R.string.download));
//        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Activity activity = ((Activity) mContext);
        Intent intent = new Intent(activity, AppDetailActivity.class);
        intent.putExtra("appInfo", getData().get(position));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImageView ivIcon = ButterKnife.findById(view, R.id.iv_icon);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                    (activity, ivIcon, ivIcon.getTransitionName());
            activity.startActivityForResult(intent, 0, options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_more:
                showPopupMenu(view.getContext(), view, position);
                break;
        }
    }

    private void showPopupMenu(final Context context, View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_app, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                LoggerUtils.e("delete -> DownloadUrl: " + getItem(position).getAppDownloadInfo().getDownloadUrl());
                RxDownload.getInstance(context)
                        .deleteServiceDownload(getItem(position).getAppDownloadInfo().getDownloadUrl(), true)
                        .doFinally(new Action() {

                            @Override
                            public void run() throws Exception {
                                remove(position);
                            }
                        }).subscribe();
                return false;
            }
        });
        popupMenu.show();
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
        @BindView(R.id.btn)
        Button btn;
        @BindView(R.id.iv_more)
        ImageView ivMore;

        public Holder(View itemView) {
            super(itemView);

        }
    }

    public static class Builder {

        private boolean isShowPosition;
        private boolean isShowMore;

        public Builder showPosition(boolean b) {
            isShowPosition = b;
            return this;
        }

        public Builder showMore(boolean b) {
            isShowMore = b;
            return this;
        }

        public AppAdatper build() {
            return new AppAdatper(this);
        }
    }

}
