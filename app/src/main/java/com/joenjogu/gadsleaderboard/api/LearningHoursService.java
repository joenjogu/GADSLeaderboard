package com.joenjogu.gadsleaderboard.api;

import com.joenjogu.gadsleaderboard.models.LearningHours;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearningHoursService {

    @GET("api/hours")
    Call<List<LearningHours>> getLearningHoursLeaderboard();
}
