package com.joenjogu.gadsleaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillService {

    @GET("api/skilliq")
    Call<List<SkillIQ>> getSkillIqLeaderboard();
}
