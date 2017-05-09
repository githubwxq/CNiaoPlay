package com.chhd.per_library.ui.base;

import android.view.View;

import org.xutils.x;

/**
 * Created by CWQ on 2017/3/26.
 */

public class BaseHolder {

    public BaseHolder(View itemView) {
        x.view().inject(this, itemView);
    }

}
