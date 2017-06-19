package com.chhd.cniaoplay.global;

import com.chhd.per_library.ui.decoration.SpaceItemDecoration;
import com.chhd.per_library.util.UiUtils;

/**
 * Created by CWQ on 2017/5/4.
 */

public interface Constant {

    String TAG = "debug_Logger";

    int SPACE_FOR_APP = 8;

    SpaceItemDecoration appSpaceItemDecoration = new SpaceItemDecoration(UiUtils.dp2px(SPACE_FOR_APP),
            SpaceItemDecoration.VERTICAL, true);
}
