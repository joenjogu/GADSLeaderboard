package com.joenjogu.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1){
            return new SkillFragment();
        }
        return new LearningFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
