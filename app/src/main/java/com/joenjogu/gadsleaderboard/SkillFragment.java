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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SkillFragment extends Fragment {

    private NetworkRepository repository;
    private SkillViewModelFactory skillViewModelFactory;
    private SkillViewModel skillViewModel;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = getView().findViewById(R.id.iq_recyclerView);
        SkillAdapter adapter = new SkillAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        repository = new NetworkRepository();
        skillViewModelFactory = new SkillViewModelFactory(repository);
        skillViewModel = new ViewModelProvider(this, skillViewModelFactory).get(SkillViewModel.class);

        skillViewModel.getSkillIQ().observe(getViewLifecycleOwner(), skillIQList ->{

            adapter.setSkillIQ(skillIQList);

        });

    }
}
