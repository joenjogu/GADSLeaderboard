package com.joenjogu.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HoursViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private NetworkRepository repository;

    public HoursViewModelFactory(NetworkRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HoursViewModel(repository);
    }
}
