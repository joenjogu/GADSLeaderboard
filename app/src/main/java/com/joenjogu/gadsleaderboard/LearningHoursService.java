package com.joenjogu.gadsleaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearningHoursService {

    @GET
    Call<List<LearningHours>> getLearningHoursLeaderboard();
}
