package com.chhd.cniaoplay.http;

import com.chhd.cniaoplay.bean.AppDownloadInfo;
import com.chhd.cniaoplay.bean.AppInfo;
import com.chhd.cniaoplay.bean.AppInfoDetail;
import com.chhd.cniaoplay.bean.BaseBean;
import com.chhd.cniaoplay.bean.Category;
import com.chhd.cniaoplay.bean.LoginBean;
import com.chhd.cniaoplay.bean.PageBean;
import com.chhd.cniaoplay.bean.RecommendBean;
import com.chhd.cniaoplay.bean.SearchResult;
import com.chhd.cniaoplay.bean.Subject;
import com.chhd.cniaoplay.bean.SubjectDetail;
import com.chhd.cniaoplay.bean.request.LoginRequestParam;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by CWQ on 2017/5/6.
 */

public interface ApiService {

    String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";
    String BASE_IMG_URL = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

    @GET("featured2")
    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

    @GET("index")
    Observable<BaseBean<RecommendBean>> getRecommendData();

    @GET("toplist")
    Observable<BaseBean<PageBean<AppInfo>>> getRankData(@Query("page") int page);

    @GET("game")
    Observable<BaseBean<PageBean<AppInfo>>> getGameData(@Query("page") int page);

    @POST("login")
    Observable<BaseBean<LoginBean>> login(@Body LoginRequestParam param);

    @GET("category")
    Observable<BaseBean<List<Category>>> getCategoryData();

    @GET("category/featured/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppDataByCategory
            (@Path("categoryid") int categoryId, @Query("page") int page);

    @GET("category/toplist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getTopListAppDataByCategory
            (@Path("categoryid") int categoryId, @Query("page") int page);

    @GET("category/newlist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getNewListAppDataByCategory
            (@Path("categoryid") int categoryId, @Query("page") int page);

    @GET("app/{id}")
    Observable<BaseBean<AppInfoDetail>> getAppDetailData(@Path("id") int id);

    @GET("download/{id}")
    Observable<BaseBean<AppDownloadInfo>> getAppDownloadInfoData(@Path("id") int id);

    @GET("apps/updateinfo")
    Observable<BaseBean<List<AppInfo>>> getAppUpdateInfoData
            (@Query("packageName") String packageName, @Query("versionCode") String versionCode);

    @GET("subject/hot")
    Observable<BaseBean<PageBean<Subject>>> getSubjectData(@Query("page") int page);

    @GET("subject/{id}")
    Observable<BaseBean<SubjectDetail>> getSubjectDetailData(@Path("id") int id);

    @GET("search/suggest")
    Observable<BaseBean<List<String>>> getSearchSuggestData(@Query("keyword") String keyword);

    @GET("search")
    Observable<BaseBean<SearchResult>> getSearchResultData(@Query("keyword") String keyword);
}
