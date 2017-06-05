package com.chhd.cniaoplay.bean;

import java.util.List;

/**
 * Created by CWQ on 2017/5/8.
 */

public class PageBean<T> {

    private boolean hasMore;
    private List<T> datas;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
