package com.joenjogu.gadsleaderboard.models;

import com.google.gson.annotations.SerializedName;

public class SkillIQ {

    @SerializedName("badgeUrl")
    private String mBadgeUrl;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("name")
    private String mName;
    @SerializedName("score")
    private Long mScore;

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

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getScore() {
        return mScore;
    }

    public void setScore(Long score) {
        mScore = score;
    }

}
