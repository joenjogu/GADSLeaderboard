package com.joenjogu.gadsleaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class LearningFragment extends Fragment {

    private HoursViewModel hoursViewModel;
    private NetworkRepository repository;
    private HoursViewModelFactory hoursViewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learning, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        repository = new NetworkRepository();
        hoursViewModelFactory = new HoursViewModelFactory(repository);
        hoursViewModel = new ViewModelProvider(this, hoursViewModelFactory).get(HoursViewModel.class);

    }
}
