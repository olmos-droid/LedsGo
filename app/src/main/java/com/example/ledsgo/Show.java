package com.example.ledsgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Class
 */
public class Show extends AppCompatActivity {
    private static final String TAG = "Show";
    private ExecutorService service;
    private DeviceRegistry registry;
    private TestObserver testObserver;

    PopupWindow popUp;
    boolean click = true;



    /**
     * dialog builder
     */
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText stripID, numTotalLeds;
    private Button btn_addStrip, btn_cancel;


    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private GeneralFragment generalFragment;
    private GroupsFragment groupsFragment;
    private StripsFragment stripsFragment;

    //Buttons add strips
    private Button button_show_strip_add;
    private Button button_show_strip1;
    private Button button_show_strip2;
    private Button button_show_strip3;
    private Button button_show_strip4;
    private Button button_show_strip5;
    private Button button_show_strip6;
    private Button button_show_strip7;
    private Button button_show_strip8;

    //Buttons add groups
    private Button button_show_group_add;
    private Button button_show_group1;
    private Button button_show_group2;
    private Button button_show_group3;
    private Button button_show_group4;
    private Button button_show_group5;
    private Button button_show_group6;
    private Button button_show_group7;
    private Button button_show_group8;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);

        service = Executors.newFixedThreadPool(8);
        registry = new DeviceRegistry();
        testObserver = new TestObserver();

        registry.addObserver(testObserver);

        registry.setExtraDelay(0);
        registry.setAutoThrottle(true);
        registry.setAntiLog(true);
        registry.startPushing();


        button_show_strip_add = findViewById(R.id.button_show_strip_add);
        button_show_strip1 = findViewById(R.id.button_show_strip1);
        button_show_strip2 = findViewById(R.id.button_show_strip2);
        button_show_strip3 = findViewById(R.id.button_show_strip3);
        button_show_strip4 = findViewById(R.id.button_show_strip4);
        button_show_strip5 = findViewById(R.id.button_show_strip5);
        button_show_strip6 = findViewById(R.id.button_show_strip6);
        button_show_strip7 = findViewById(R.id.button_show_strip7);
        button_show_strip8 = findViewById(R.id.button_show_strip8);
        stripID = findViewById(R.id.editTextNumberStrip);
        numTotalLeds = findViewById(R.id.editTextNumberTotalLeds);
        btn_addStrip = findViewById(R.id.button_Add_strip);
        btn_cancel = findViewById(R.id.buttonDialogCancel);


/**
 *hooks dels groups
 */
        button_show_group_add = findViewById(R.id.button_show_group_add);
        button_show_group1 = findViewById(R.id.button_show_group1);
        button_show_group2 = findViewById(R.id.button_show_group2);
        button_show_group3 = findViewById(R.id.button_show_group3);
        button_show_group4 = findViewById(R.id.button_show_group4);
        button_show_group5 = findViewById(R.id.button_show_group5);
        button_show_group6 = findViewById(R.id.button_show_group6);
        button_show_group7 = findViewById(R.id.button_show_group7);
        button_show_group8 = findViewById(R.id.button_show_group8);


        mMainFrame = findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.main_nav);

        generalFragment = new GeneralFragment(registry,testObserver,service);
        groupsFragment = new GroupsFragment(registry,testObserver,service);
        stripsFragment = new StripsFragment(registry,testObserver,service);

        //registry.setExtraDelay(0);

        button_show_strip_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewStripDialog();
            }
        });


        setFragment(generalFragment);


        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //        Log.d(TAG, "onNavigationItemSelected: cargando por primera vez " + item);

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

    /**
     * @param fragment
     */
    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }


    public void createNewStripDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactViewPopUP = getLayoutInflater().inflate(R.layout.popup, null);


        dialogBuilder.setView(contactViewPopUP);
        dialog = dialogBuilder.create();
        dialog.show();
        btn_addStrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }


}