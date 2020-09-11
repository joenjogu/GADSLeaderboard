package com.joenjogu.gadsleaderboard.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.joenjogu.gadsleaderboard.repository.NetworkRepository;

@SuppressWarnings("unchecked")
public class SkillViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private NetworkRepository repository;
    public SkillViewModelFactory(NetworkRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SkillViewModel(repository);
    }
}
