package com.example.ledsgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Show extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private GeneralFragment generalFragment;
    private GroupsFragment groupsFragment;
    private StripsFragment stripsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        mMainFrame = findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.main_nav);

        generalFragment = new GeneralFragment();
        groupsFragment = new GroupsFragment();
        stripsFragment = new StripsFragment();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_menu_general:
                        //open the HomeFragment
                        //We can change the color of the background using the following:
                        mMainNav.setItemBackgroundResource(R.color.purple_100);
                        setFragment(generalFragment);
                        return true;
                    case R.id.nav_menu_strips:
                        //open the ProfileFragment
                        mMainNav.setItemBackgroundResource(R.color.blue_100);
                        setFragment(stripsFragment);
                        return true;
                    case R.id.nav_menu_groups:
                        //open the SettingsFragment
                        mMainNav.setItemBackgroundResource(R.color.orange_100);
                        setFragment(groupsFragment);
                        return true;
                    default:
                        return false;
                }

            }
        });
    }
    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}