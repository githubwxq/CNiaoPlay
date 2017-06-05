package com.chhd.cniaoplay.ui.base;

import android.animation.ObjectAnimator;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.chhd.cniaoplay.global.Constant;
import com.chhd.cniaoplay.util.LoggerUtils;
import com.chhd.per_library.ui.base.BaseFragment;
import com.chhd.per_library.util.UiUtils;

/**
 * Created by CWQ on 2017/5/4.
 */

public abstract class SimpleFragment extends BaseFragment implements Constant, View.OnTouchListener {

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ObjectAnimator upAnim = ObjectAnimator.ofFloat(view, "translationZ", UiUtils.dp2px(3));
                upAnim.setDuration(150);
                upAnim.setInterpolator(new DecelerateInterpolator());
                upAnim.start();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                ObjectAnimator downAnim = ObjectAnimator.ofFloat(view, "translationZ", 0);
                downAnim.setDuration(150);
                downAnim.setInterpolator(new AccelerateInterpolator());
                downAnim.start();
                break;
        }
        return true;
    }
}
