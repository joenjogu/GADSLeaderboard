package com.joenjogu.gadsleaderboard;

import java.util.List;

import retrofit2.Call;

public class NetworkRepository {

    private LearningHoursService hoursService;
    private SkillService skillService;

    public Call<List<LearningHours>> getHoursCall() {
        hoursService = ServiceBuilder.buildService(LearningHoursService.class);
        Call<List<LearningHours>> hoursCall = hoursService.getLearningHoursLeaderboard();
        return hoursCall;
    }

    public Call<List<SkillIQ>> getSkillCall() {
        skillService = ServiceBuilder.buildService(SkillService.class);
        Call<List<SkillIQ>> skillCall = skillService.getSkillIqLeaderboard();
        return skillCall;
    }
}
