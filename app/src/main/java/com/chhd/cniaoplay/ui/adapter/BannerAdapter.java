package com.chhd.cniaoplay.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.ui.card.CardAdapter;
import com.chhd.cniaoplay.util.LoggerUtils;
import com.chhd.per_library.util.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CWQ on 2017/5/31.
 */

public class BannerAdapter extends PagerAdapter implements CardAdapter {

    private List<String> imgs;
    private List<CardView> cardViews = new ArrayList<>();

    private float mBaseElevation;

    public BannerAdapter(List<String> imgs) {
        this.imgs = imgs;

        for (int i = 0; i < imgs.size(); i++) {
            cardViews.add(null);
        }
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return cardViews.get(position);
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.item_list_banner, container, false);
        container.addView(view);

        CardView cardView = (CardView) view.findViewById(R.id.card_view);
        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        cardViews.set(position, cardView);

        ImageView ivPic = (ImageView) view.findViewById(R.id.iv_pic);
        Glide.with(container.getContext())
                .load(imgs.get(position))
                .placeholder(R.drawable.empty_photo)
                .error(R.drawable.empty_photo)
                .centerCrop()
                .dontAnimate()
                .into(ivPic);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        cardViews.set(position, null);
    }

}
