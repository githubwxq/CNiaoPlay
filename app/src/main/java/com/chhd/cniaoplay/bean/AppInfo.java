package com.chhd.cniaoplay.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by CWQ on 2017/5/8.
 */

public class AppInfo implements Parcelable {


    /**
     * adType : 0
     * addTime : 0
     * ads : 0
     * apkSize : 13070713
     * appendSize : 0
     * briefShow : 小米官方出品，1分钟极速放款
     * briefUseIntro : false
     * developerId : 0
     * diffFileSize : 0
     * displayName : 小米贷款-新人免息
     * favorite : false
     * fitness : 0
     * grantCode : 0
     * hasSameDevApp : false
     * icon : AppStore/04b321507c38a4b6704b2cec9ac1e632ec18f0e0b
     * id : 452551
     * isFavorite : false
     * level1CategoryId : 1
     * level1CategoryName : 金融理财
     * level2CategoryId : 229
     * packageName : com.xiaomi.loan
     * position : 1
     * publisherName : 上海小米金融信息服务有限公司
     * rId : 0
     * ratingScore : 2.5
     * ratingTotalCount : 0
     * relateAppHasMore : false
     * releaseKeyHash : f634a7f9566767ce6cc75a87d121dc16
     * samDevAppHasMore : false
     * screenshot : AppStore/09d6e14864b064a7b3de9f81919be5255d87c3aac,AppStore/02d6e14864b064a7e3de988191ebe52f5d8743aac,AppStore/067774febaccd74e899626279fc0c21030f40820b,AppStore/067774febaccd74e8296272791c0c7103df40820b,AppStore/05bf0405d811082eea3f8e8952b03be933e430c1d
     * source :
     * suitableType : 0
     * updateTime : 1494123235751
     * versionCode : 1
     * versionName : 1.0.0
     * videoId : 0
     */

    private int adType;
    private int addTime;
    private int ads;
    private int apkSize;
    private int appendSize;
    private String briefShow;
    private boolean briefUseIntro;
    private int developerId;
    private int diffFileSize;
    private String displayName;
    private boolean favorite;
    private int fitness;
    private int grantCode;
    private boolean hasSameDevApp;
    private String icon;
    private int id;
    private boolean isFavorite;
    private int level1CategoryId;
    private String level1CategoryName;
    private int level2CategoryId;
    private String packageName;
    private int position;
    private String publisherName;
    private int rId;
    private double ratingScore;
    private int ratingTotalCount;
    private boolean relateAppHasMore;
    private String releaseKeyHash;
    private boolean samDevAppHasMore;
    private String screenshot;
    private String source;
    private int suitableType;
    private long updateTime;
    private int versionCode;
    private String versionName;
    private int videoId;

    private AppDownloadInfo appDownloadInfo;

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getAds() {
        return ads;
    }

    public void setAds(int ads) {
        this.ads = ads;
    }

    public int getApkSize() {
        return apkSize;
    }

    public void setApkSize(int apkSize) {
        this.apkSize = apkSize;
    }

    public int getAppendSize() {
        return appendSize;
    }

    public void setAppendSize(int appendSize) {
        this.appendSize = appendSize;
    }

    public String getBriefShow() {
        return briefShow;
    }

    public void setBriefShow(String briefShow) {
        this.briefShow = briefShow;
    }

    public boolean isBriefUseIntro() {
        return briefUseIntro;
    }

    public void setBriefUseIntro(boolean briefUseIntro) {
        this.briefUseIntro = briefUseIntro;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public int getDiffFileSize() {
        return diffFileSize;
    }

    public void setDiffFileSize(int diffFileSize) {
        this.diffFileSize = diffFileSize;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getGrantCode() {
        return grantCode;
    }

    public void setGrantCode(int grantCode) {
        this.grantCode = grantCode;
    }

    public boolean isHasSameDevApp() {
        return hasSameDevApp;
    }

    public void setHasSameDevApp(boolean hasSameDevApp) {
        this.hasSameDevApp = hasSameDevApp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getLevel1CategoryId() {
        return level1CategoryId;
    }

    public void setLevel1CategoryId(int level1CategoryId) {
        this.level1CategoryId = level1CategoryId;
    }

    public String getLevel1CategoryName() {
        return level1CategoryName;
    }

    public void setLevel1CategoryName(String level1CategoryName) {
        this.level1CategoryName = level1CategoryName;
    }

    public int getLevel2CategoryId() {
        return level2CategoryId;
    }

    public void setLevel2CategoryId(int level2CategoryId) {
        this.level2CategoryId = level2CategoryId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getRId() {
        return rId;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public int getRatingTotalCount() {
        return ratingTotalCount;
    }

    public void setRatingTotalCount(int ratingTotalCount) {
        this.ratingTotalCount = ratingTotalCount;
    }

    public boolean isRelateAppHasMore() {
        return relateAppHasMore;
    }

    public void setRelateAppHasMore(boolean relateAppHasMore) {
        this.relateAppHasMore = relateAppHasMore;
    }

    public String getReleaseKeyHash() {
        return releaseKeyHash;
    }

    public void setReleaseKeyHash(String releaseKeyHash) {
        this.releaseKeyHash = releaseKeyHash;
    }

    public boolean isSamDevAppHasMore() {
        return samDevAppHasMore;
    }

    public void setSamDevAppHasMore(boolean samDevAppHasMore) {
        this.samDevAppHasMore = samDevAppHasMore;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSuitableType() {
        return suitableType;
    }

    public void setSuitableType(int suitableType) {
        this.suitableType = suitableType;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }


    public AppDownloadInfo getAppDownloadInfo() {
        return appDownloadInfo;
    }

    public void setAppDownloadInfo(AppDownloadInfo appDownloadInfo) {
        this.appDownloadInfo = appDownloadInfo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.adType);
        dest.writeInt(this.addTime);
        dest.writeInt(this.ads);
        dest.writeInt(this.apkSize);
        dest.writeInt(this.appendSize);
        dest.writeString(this.briefShow);
        dest.writeByte(this.briefUseIntro ? (byte) 1 : (byte) 0);
        dest.writeInt(this.developerId);
        dest.writeInt(this.diffFileSize);
        dest.writeString(this.displayName);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
        dest.writeInt(this.fitness);
        dest.writeInt(this.grantCode);
        dest.writeByte(this.hasSameDevApp ? (byte) 1 : (byte) 0);
        dest.writeString(this.icon);
        dest.writeInt(this.id);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
        dest.writeInt(this.level1CategoryId);
        dest.writeString(this.level1CategoryName);
        dest.writeInt(this.level2CategoryId);
        dest.writeString(this.packageName);
        dest.writeInt(this.position);
        dest.writeString(this.publisherName);
        dest.writeInt(this.rId);
        dest.writeDouble(this.ratingScore);
        dest.writeInt(this.ratingTotalCount);
        dest.writeByte(this.relateAppHasMore ? (byte) 1 : (byte) 0);
        dest.writeString(this.releaseKeyHash);
        dest.writeByte(this.samDevAppHasMore ? (byte) 1 : (byte) 0);
        dest.writeString(this.screenshot);
        dest.writeString(this.source);
        dest.writeInt(this.suitableType);
        dest.writeLong(this.updateTime);
        dest.writeInt(this.versionCode);
        dest.writeString(this.versionName);
        dest.writeInt(this.videoId);
        dest.writeParcelable(this.appDownloadInfo, flags);
    }

    public AppInfo() {
    }

    protected AppInfo(Parcel in) {
        this.adType = in.readInt();
        this.addTime = in.readInt();
        this.ads = in.readInt();
        this.apkSize = in.readInt();
        this.appendSize = in.readInt();
        this.briefShow = in.readString();
        this.briefUseIntro = in.readByte() != 0;
        this.developerId = in.readInt();
        this.diffFileSize = in.readInt();
        this.displayName = in.readString();
        this.favorite = in.readByte() != 0;
        this.fitness = in.readInt();
        this.grantCode = in.readInt();
        this.hasSameDevApp = in.readByte() != 0;
        this.icon = in.readString();
        this.id = in.readInt();
        this.isFavorite = in.readByte() != 0;
        this.level1CategoryId = in.readInt();
        this.level1CategoryName = in.readString();
        this.level2CategoryId = in.readInt();
        this.packageName = in.readString();
        this.position = in.readInt();
        this.publisherName = in.readString();
        this.rId = in.readInt();
        this.ratingScore = in.readDouble();
        this.ratingTotalCount = in.readInt();
        this.relateAppHasMore = in.readByte() != 0;
        this.releaseKeyHash = in.readString();
        this.samDevAppHasMore = in.readByte() != 0;
        this.screenshot = in.readString();
        this.source = in.readString();
        this.suitableType = in.readInt();
        this.updateTime = in.readLong();
        this.versionCode = in.readInt();
        this.versionName = in.readString();
        this.videoId = in.readInt();
        this.appDownloadInfo = in.readParcelable(AppDownloadInfo.class.getClassLoader());
    }

    public static final Creator<AppInfo> CREATOR = new Creator<AppInfo>() {
        @Override
        public AppInfo createFromParcel(Parcel source) {
            return new AppInfo(source);
        }

        @Override
        public AppInfo[] newArray(int size) {
            return new AppInfo[size];
        }
    };
}
