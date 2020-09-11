package com.joenjogu.gadsleaderboard.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.joenjogu.gadsleaderboard.R;
import com.joenjogu.gadsleaderboard.adapters.FragmentAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        Button submit = findViewById(R.id.menu_btn_submit);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(
                getSupportFragmentManager(),
                getLifecycle());
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(fragmentAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout,
                viewPager,
                true,
                (tab, position) -> {
            if(position == 1){
                tab.setText(R.string.skill_iq_leaders);
            }else {
                tab.setText(R.string.learning_leaders);
            }
            viewPager.setCurrentItem(tab.getPosition(), true);
        });
        tabLayoutMediator.attach();

        submit.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SubmissionActivity.class);
            startActivity(intent);
        });
    }
}