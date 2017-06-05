package com.chhd.cniaoplay.bean;

import android.os.Bundle;

/**
 * Created by CWQ on 2017/6/1.
 */

public class MessageEvent {

    private String action;

    private Bundle bundle;

    public MessageEvent(String action) {
        this.action = action;
    }

    public MessageEvent(String action, Bundle bundle) {
        this.action = action;
        this.bundle = bundle;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
