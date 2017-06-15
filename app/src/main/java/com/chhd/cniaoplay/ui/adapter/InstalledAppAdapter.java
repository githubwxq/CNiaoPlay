package com.chhd.cniaoplay.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.InstalledApp;
import com.chhd.cniaoplay.ui.base.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CWQ on 2017/6/13.
 */

public class InstalledAppAdapter extends SimpleAdapter<InstalledApp, BaseViewHolder> implements BaseQuickAdapter.OnItemChildClickListener {

    public InstalledAppAdapter() {
        super(R.layout.item_list_installed_app, new ArrayList<InstalledApp>());

        setOnItemChildClickListener(this);
    }

    @Override
    protected void convert(BaseViewHolder helper, InstalledApp item) {
        helper.setImageDrawable(R.id.iv_icon, item.getIcon());
        helper.setText(R.id.tv_app_name, item.getAppName());
        helper.addOnClickListener(R.id.btn);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        uninstall(getData().get(position).getPackageName());
    }

    public void uninstall(String packageName) {
        Intent i = new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + packageName));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }
}
