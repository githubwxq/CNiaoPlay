package com.chhd.cniaoplay.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chhd.cniaoplay.R;
import com.chhd.cniaoplay.bean.RecommendBean;
import com.chhd.cniaoplay.global.Constant;
import com.chhd.cniaoplay.ui.card.ShadowTransformer;
import com.chhd.cniaoplay.ui.fragment.main.RecommendFragment;
import com.chhd.per_library.ui.decoration.SpaceItemDecoration;
import com.chhd.per_library.util.UiUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CWQ on 2017/5/23.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Constant {

    private final int ITEM_BANNER = 0;
    private final int ITEM_NAV = 1;
    private final int ITEM_APP = 2;
    private final int ITEM_GAME = 3;

    private Context context;
    private RecommendBean recommendBean;
    private RecommendFragment fragment;

    public RecommendAdapter(Context context, RecommendBean recommendBean, RecommendFragment fragment) {
        this.context = context;
        this.recommendBean = recommendBean;
        this.fragment = fragment;
    }

    public void setRecommendBean(RecommendBean recommendBean) {
        this.recommendBean = recommendBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_BANNER) {
            return new ViewPagerHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_list_recommend_view_pager, parent, false));
        } else if (viewType == ITEM_NAV) {
            return new NavHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_list_recommend_nav, parent, false));
        } else if (viewType == ITEM_APP) {
            return new AppHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_list_recommend_hot_app, parent, false));
        } else if (viewType == ITEM_GAME) {
            return new AppHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_list_recommend_hot_app, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder.getItemViewType() == ITEM_BANNER) {
            List<String> imgs = new ArrayList<>();
            for (int i = 0; i < recommendBean.getBanners().size(); i++) {
                imgs.add(recommendBean.getBanners().get(i).getThumbnail());
            }
//            BannerHolder holder = (BannerHolder) viewHolder;
//            holder.banner.setImageLoader(new GlideImageLoader());
//            holder.banner.setImages(imgs);
//            holder.banner.setIndicatorGravity(BannerConfig.RIGHT);
//            holder.banner.start();
            ViewPagerHolder holder = (ViewPagerHolder) viewHolder;
            BannerAdapter adapter = new BannerAdapter(imgs);
            ShadowTransformer transformer = new ShadowTransformer(holder.viewPager, adapter);
            transformer.enableScaling(true);
            holder.viewPager.setAdapter(adapter);
            holder.viewPager.setPageTransformer(false, transformer);
            holder.viewPager.setOffscreenPageLimit(3);
        } else if (viewHolder.getItemViewType() == ITEM_NAV) {
            NavHolder holder = (NavHolder) viewHolder;
            holder.navApp.setOnTouchListener(fragment);
            holder.navGame.setOnTouchListener(fragment);
            holder.navTheme.setOnTouchListener(fragment);
        } else if (viewHolder.getItemViewType() == ITEM_APP) {
            AppHolder holder = (AppHolder) viewHolder;
            holder.tvTitle.setText(context.getText(R.string.hot_app));
            AppAdatper adapter = new AppAdatper(recommendBean.getRecommendApps());
            holder.rvApp.setAdapter(adapter);
            holder.rvApp.setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
            holder.rvApp.removeItemDecoration(spaceItemDecoration);
            holder.rvApp.addItemDecoration(spaceItemDecoration);
        } else if (viewHolder.getItemViewType() == ITEM_GAME) {
            AppHolder holder = (AppHolder) viewHolder;
            holder.tvTitle.setText(context.getText(R.string.hot_game));
            AppAdatper adapter = new AppAdatper(recommendBean.getRecommendGames());
            holder.rvApp.setAdapter(adapter);
            holder.rvApp.setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
            holder.rvApp.removeItemDecoration(spaceItemDecoration);
            holder.rvApp.addItemDecoration(spaceItemDecoration);
        }
    }

    private SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(UiUtils.dp2px(SPACE_FOR_APP),
            SpaceItemDecoration.VERTICAL, true);

    @Override
    public int getItemCount() {
        if (recommendBean == null) {
            return 0;
        } else {
            return getObjectMethodNumber(recommendBean);
        }
    }

    private int getObjectMethodNumber(Object model) {
        Field[] fields = model.getClass().getDeclaredFields();
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_BANNER;
        } else if (position == 1) {
            return ITEM_NAV;
        } else if (position == 2) {
            return ITEM_APP;
        } else if (position == 3) {
            return ITEM_GAME;
        }
        return -1;
    }

    class BannerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewPagerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_pager)
        ViewPager viewPager;

        public ViewPagerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class NavHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nav_app)
        CardView navApp;
        @BindView(R.id.nav_game)
        CardView navGame;
        @BindView(R.id.nav_theme)
        CardView navTheme;

        public NavHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AppHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.rv_app)
        RecyclerView rvApp;

        public AppHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
