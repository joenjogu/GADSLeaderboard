package com.joenjogu.gadsleaderboard;

import java.util.List;

import retrofit2.Call;

@SuppressWarnings("FieldCanBeLocal")
public class NetworkRepository {

    private LearningHoursService hoursService;
    private SkillService skillService;
    private SubmissionService submissionService;

    public Call<List<LearningHours>> getHoursCall() {
        hoursService = ServiceBuilder.buildService(LearningHoursService.class);
        return hoursService.getLearningHoursLeaderboard();
    }

    public Call<List<SkillIQ>> getSkillCall() {
        skillService = ServiceBuilder.buildService(SkillService.class);
        return skillService.getSkillIqLeaderboard();
    }

    public Call<Void> postUserDetails(String firstName, String lastName, String email, String link) {
        submissionService = ServiceBuilder.buildService(SubmissionService.class);
        return submissionService.submitUserDetails(firstName, lastName, email, link);
    }
}
