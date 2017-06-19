package com.chhd.cniaoplay.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.ui.base.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CWQ on 2017/6/15.
 */

public class SearchSuggestAdapter extends SimpleAdapter<String, BaseViewHolder> {

    public SearchSuggestAdapter() {
        super(R.layout.item_list_search_suggest, new ArrayList<String>());
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_name, item);
    }
}
