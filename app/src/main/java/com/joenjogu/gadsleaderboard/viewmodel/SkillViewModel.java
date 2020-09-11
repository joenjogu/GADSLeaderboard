package com.joenjogu.gadsleaderboard.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joenjogu.gadsleaderboard.models.SkillIQ;
import com.joenjogu.gadsleaderboard.repository.NetworkRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillViewModel extends ViewModel {

    private NetworkRepository repository;

    public SkillViewModel(NetworkRepository repository) {
        this.repository = repository;
    }

    private MutableLiveData<List<SkillIQ>> skillIQ;

    public LiveData<List<SkillIQ>> getSkillIQ(){
        if (skillIQ == null){
            skillIQ = new MutableLiveData<>();

            skillIQCall(this.repository);
        }
        return skillIQ;
    }

    private void skillIQCall(NetworkRepository repository) {
        repository.getSkillCall().enqueue(new Callback<List<SkillIQ>>() {
            @Override
            public void onResponse(@NotNull Call<List<SkillIQ>> call,
                                   @NotNull Response<List<SkillIQ>> response) {
                if (response.isSuccessful()){
                    skillIQ.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<SkillIQ>> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
