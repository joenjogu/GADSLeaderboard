package com.joenjogu.gadsleaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

@SuppressWarnings("FieldCanBeLocal")
public class LearningFragment extends Fragment {

    private HoursViewModel hoursViewModel;
    private NetworkRepository repository;
    private HoursViewModelFactory hoursViewModelFactory;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learning, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProgressBar progressBar = Objects.requireNonNull(
                getView()
        ).findViewById(R.id.learning_progress_bar);
        RecyclerView recyclerView = getView().findViewById(R.id.hours_recyclerView);
        LearningAdapter adapter = new LearningAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        repository = new NetworkRepository();
        hoursViewModelFactory = new HoursViewModelFactory(repository);
        hoursViewModel = new ViewModelProvider(
                this,
                hoursViewModelFactory
        ).get(HoursViewModel.class);

        progressBar.setVisibility(View.VISIBLE);

        hoursViewModel.getLearningHours().observe(getViewLifecycleOwner(), learningHoursList -> {

            adapter.setLearningHours(learningHoursList);
            progressBar.setVisibility(View.GONE);
        });

    }
}
