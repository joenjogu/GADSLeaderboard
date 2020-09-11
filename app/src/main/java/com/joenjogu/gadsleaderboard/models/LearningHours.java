package com.joenjogu.gadsleaderboard.models;

import com.google.gson.annotations.SerializedName;

public class LearningHours {

    @SerializedName("badgeUrl")
    private String mBadgeUrl;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("hours")
    private Long mHours;
    @SerializedName("name")
    private String mName;

    public String getBadgeUrl() {
        return mBadgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        mBadgeUrl = badgeUrl;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public Long getHours() {
        return mHours;
    }

    public void setHours(Long hours) {
        mHours = hours;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
