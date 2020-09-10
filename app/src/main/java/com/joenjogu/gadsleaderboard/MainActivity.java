package com.joenjogu.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar topAppBar = findViewById(R.id.topAppBar);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        Button submit = findViewById(R.id.menu_btn_submit);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(
                getSupportFragmentManager(),
                getLifecycle());
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(fragmentAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, (tab, position) -> {
            if(position == 1){
                tab.setText(R.string.skill_iq_leaders);
            }else {
                tab.setText(R.string.learning_leaders);
            }
            viewPager.setCurrentItem(tab.getPosition(), true);
        });
        tabLayoutMediator.attach();

        submit.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),SubmissionActivity.class);
            startActivity(intent);
        });


//        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if (item.getItemId() == R.id.menu_icon_submit) {
//                    Log.d(TAG, "onMenuItemClick: Submit Clicked");
//                    //Intent for Submission Activity
//                    Intent intent = new Intent(getApplicationContext(),SubmissionActivity.class);
//                    startActivity(intent);
//                    return true;
//                }
//                Log.d(TAG, "onMenuItemClick: Submit Click Failed");
//                return false;
//            }
//        });

    }
}