package com.example.ledsgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heroicrobot.dropbit.devices.pixelpusher.Strip;
import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Show extends AppCompatActivity {
    private static final String TAG = "Show";

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private GeneralFragment generalFragment;
    private GroupsFragment groupsFragment;
    private StripsFragment stripsFragment;

    private DeviceRegistry registry = new DeviceRegistry();
    private TestObserver testObserver = new TestObserver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        mMainFrame = findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.main_nav);

        generalFragment = new GeneralFragment(registry, testObserver);
        groupsFragment = new GroupsFragment();
        stripsFragment = new StripsFragment();

        registry.startPushing();
        registry.setExtraDelay(0);
        registry.setAutoThrottle(true);

        setFragment(generalFragment);

        List<Strip> strips = registry.getStrips();
        prepareExitHandler(registry);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d(TAG, "onNavigationItemSelected: cargando por primera vez " + item);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    public void prepareExitHandler(DeviceRegistry registry) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                Log.d(TAG, "run: Shutdown hook running");
                List<Strip> strips = registry.getStrips();
                for (Strip strip : strips)
                {
                    for (int i = 0; i < strip.getLength(); i++)
                    {
                        strip.setPixel(0, i);
                    }
                }
            }
        }
        ));
    }

}