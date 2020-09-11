package com.joenjogu.gadsleaderboard.api;

import com.joenjogu.gadsleaderboard.models.SkillIQ;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillService {

    @GET("api/skilliq")
    Call<List<SkillIQ>> getSkillIqLeaderboard();
}
