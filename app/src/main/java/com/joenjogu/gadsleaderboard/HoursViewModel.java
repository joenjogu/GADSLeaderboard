package com.joenjogu.gadsleaderboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoursViewModel extends ViewModel {
    private NetworkRepository repository;

    public HoursViewModel(NetworkRepository repository) {
        this.repository = repository;
    }

    private MutableLiveData<List<LearningHours>> learningHours;

    public LiveData<List<LearningHours>> getLearningHours(){
        if(learningHours == null){
            learningHours = new MutableLiveData<List<LearningHours>>();

            learningHoursCall(this.repository);
        }
        return learningHours;
    }

    private void learningHoursCall(NetworkRepository repository) {
        repository.getHoursCall().enqueue(new Callback<List<LearningHours>>() {
            @Override
            public void onResponse(@NotNull Call<List<LearningHours>> call,
                                   @NotNull Response<List<LearningHours>> response) {
                if (response.isSuccessful()){
                    learningHours.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<LearningHours>> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
