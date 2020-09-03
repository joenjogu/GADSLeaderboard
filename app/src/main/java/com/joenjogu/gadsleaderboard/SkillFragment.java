package com.joenjogu.gadsleaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SkillFragment extends Fragment {

    private NetworkRepository repository;
    private SkillViewModelFactory skillViewModelFactory;
    private SkillViewModel skillViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skill, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        repository = new NetworkRepository();
        skillViewModelFactory = new SkillViewModelFactory(repository);
        skillViewModel = new ViewModelProvider(this, skillViewModelFactory).get(SkillViewModel.class);



    }
}
