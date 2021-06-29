package com.example.myapplication30;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heroicrobot.dropbit.registry.DeviceRegistry;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Show extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Show";

    public static ExecutorService service;
    public static DeviceRegistry registry,registrygroups,registrystrips;
    public static TestObserver testObserver,testObservergroups;
    private FrameLayout mMainFrame;
    private GeneralFragment generalFragment;
    private GroupsFragment groupsFragment;
    private StripsFragment stripsFragment;
    private Button buttonGeneral, buttonStrips, buttonGroups, buttonAddStrip, buttonAddGroup, buttonRestart;
    private Button buttonStrip1, buttonStrip2, buttonStrip3, buttonStrip4, buttonStrip5, buttonStrip6, buttonStrip7, buttonStrip8;
    private Button buttonGroup1, buttonGroup2, buttonGroup3, buttonGroup4, buttonGroup5, buttonGroup6, buttonGroup7, buttonGroup8;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView textViewUser;
    private ImageView imageViewUser;
    private String topefirst, topelast, edittextLeds;
    private String totalName;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Bitmap bmp;
    private View verticalLayoutPerfil;
    private DatabaseReference mDatabase;
    private ArrayList<Tira> tiras = new ArrayList<>();
    private ArrayList<String> groupsArrayList = new ArrayList<>();
    private String id = "ASDASD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        service = Executors.newFixedThreadPool(8);
        registry = new DeviceRegistry();
        registrygroups = new DeviceRegistry();
        registrystrips = new DeviceRegistry();

        testObserver = new TestObserver();
        testObservergroups = new TestObserver();

        registry.addObserver(testObserver);
        registrygroups.addObserver(testObserver);

        registry.setExtraDelay(0);
        registry.setAutoThrottle(true);
        registry.setAntiLog(true);
        registry.startPushing();

        buttonGroup1 = findViewById(R.id.button_show_group1);
        buttonGroup2 = findViewById(R.id.button_show_group2);
        buttonGroup3 = findViewById(R.id.button_show_group3);
        buttonGroup4 = findViewById(R.id.button_show_group4);
        buttonGroup5 = findViewById(R.id.button_show_group5);
        buttonGroup6 = findViewById(R.id.button_show_group6);
        buttonGroup7 = findViewById(R.id.button_show_group7);
        buttonGroup8 = findViewById(R.id.button_show_group8);

        buttonStrip1 = findViewById(R.id.button_show_strip1);
        buttonStrip2 = findViewById(R.id.button_show_strip2);
        buttonStrip3 = findViewById(R.id.button_show_strip3);
        buttonStrip4 = findViewById(R.id.button_show_strip4);
        buttonStrip5 = findViewById(R.id.button_show_strip5);
        buttonStrip6 = findViewById(R.id.button_show_strip6);
        buttonStrip7 = findViewById(R.id.button_show_strip7);
        buttonStrip8 = findViewById(R.id.button_show_strip8);

        mMainFrame = findViewById(R.id.main_frame);

        generalFragment = new GeneralFragment(registry,testObserver,service);

        setFragment(generalFragment);

        buttonGeneral = findViewById(R.id.Button_Show_GeneralFrag);
        buttonStrips = findViewById(R.id.Button_Show_StripsFrag);
        buttonGroups = findViewById(R.id.Button_Show_GroupsFrag);
        drawerLayout = findViewById(R.id.drawer_layout);
        buttonAddStrip = findViewById(R.id.button_show_strip_add);
        buttonAddGroup = findViewById(R.id.button_show_group_add);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.mytoolbar);
        View headerView = navigationView.getHeaderView(0);
        verticalLayoutPerfil = headerView.findViewById(R.id.LinearLayout_Header_Perfil);
        textViewUser = headerView.findViewById(R.id.textView_Header_username);
        imageViewUser = headerView.findViewById(R.id.imageView_Header_User);
        sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Bundle extras = getIntent().getExtras();

        if (!(extras == null)) {

            bmp = (Bitmap) extras.getParcelable("imagebitmap");
            String bmp2 = extras.getString("user");

            textViewUser.setText(bmp2);
            imageViewUser.setImageBitmap(bmp);
        }
        String stringSharedPrefName = sharedPreferences.getString("myUserName", null);

        if (stringSharedPrefName != null) {

            stringSharedPrefName.toUpperCase();

            topefirst = stringSharedPrefName.substring(0, 1).toUpperCase();
            topelast = stringSharedPrefName.substring(1, stringSharedPrefName.length());
            totalName = topefirst + topelast;

            textViewUser.setText(totalName);
            textViewUser.setTextSize(16);
            imageViewUser.setImageResource(R.drawable.ic_baseline_person_24);
            editor.putString("myUserName", totalName);
            editor.commit();
            byte[] imageAsBytes = Base64.decode(sharedPreferences.getString("image", null).getBytes(), Base64.DEFAULT);
            imageViewUser.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        }

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(Show.this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_view);
        buttonRestart = navigationView.findViewById(R.id.menuResetPP);

//        List<Strip> strips = registry.getStrips();
//        prepareExitHandler(registry);

        buttonStrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registry.stopPushing();
                service.shutdown();
                service = Executors.newFixedThreadPool(8);
              //  registry = new DeviceRegistry();
                testObserver = new TestObserver();

                registry.addObserver(testObserver);

                registry.setExtraDelay(0);
                registry.setAutoThrottle(true);
                registry.setAntiLog(true);
                registry.startPushing();
                stripsFragment = new StripsFragment(registry,testObserver,service);
                setFragment(stripsFragment);
            }
        });
        buttonGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registry.stopPushing();
                service.shutdown();
                service = Executors.newFixedThreadPool(8);
            //    registry = new DeviceRegistry();
                testObserver = new TestObserver();

                registry.addObserver(testObserver);

                registry.setExtraDelay(0);
                registry.setAutoThrottle(true);
                registry.setAntiLog(true);
                registry.startPushing();
                generalFragment = new GeneralFragment(registry,testObserver,service);

                setFragment(generalFragment);
            }
        });
        buttonGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registry.stopPushing();
                service.shutdown();
                service = Executors.newFixedThreadPool(8);

//                registrygroups.addObserver(testObservergroups);

                registrygroups.setExtraDelay(0);
                registrygroups.setAutoThrottle(true);
                registrygroups.setAntiLog(true);
                registrygroups.startPushing();
                groupsFragment = new GroupsFragment(registrygroups ,testObservergroups,service);

                setFragment(groupsFragment);
            }
        });

        verticalLayoutPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Perfil.class);

                Bundle extras = getIntent().getExtras();
                if (!(extras == null)) {

                    extras.putParcelable("imagebitmap", bmp);
                    intent1.putExtras(extras);
                }
                startActivity(intent1);
            }
        });
        returnLength();
        System.out.println(tiras.size());

        buttonAddStrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnLength();
                String user = sharedPreferences.getString("id", null);
                int numstripint = tiras.size() + 1;
                stripsButtons(); //aconseguir que ho fasi en el onCreate no en el setOnClickListener, pero no detecta el tiras.size() perque surt valor a 0 fora del setOnClikListener
                String numstrip = String.valueOf(numstripint);
                if (!(tiras.size() > 7)) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(Show.this, R.style.myDialogSttrips));
                    builder.setTitle("Quants Leds conte la Strip?");

                    int lightColor = Color.parseColor("#FFFFFF");

                    final EditText editTextproba = new EditText(getApplicationContext());
                    editTextproba.setTextColor(lightColor);
                    editTextproba.setInputType(InputType.TYPE_CLASS_NUMBER);
                    builder.setView(editTextproba);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            edittextLeds = editTextproba.getText().toString();
                            Tira tira = new Tira(numstrip, edittextLeds);
                            mDatabase.child("Strips").child("Strips" + user).push().setValue(tira);
                            switch (tiras.size()) {
                                case 0:
                                    buttonStrip1.setText("Strip " + numstrip + "\nLeds " + edittextLeds);
                                    buttonStrip1.setTextSize(10);
                                    buttonStrip1.setWidth(88);
                                    buttonStrip1.setHeight(48);
                                    break;
                                case 1:
                                    buttonStrip2.setText("Strip " + numstrip + "\nLeds " + edittextLeds);
                                    buttonStrip2.setTextSize(10);
                                    buttonStrip2.setWidth(88);
                                    buttonStrip2.setHeight(48);
                                    break;
                                case 2:
                                    buttonStrip3.setText("Strip " + numstrip + "\nLeds " + edittextLeds);
                                    buttonStrip3.setTextSize(10);
                                    buttonStrip3.setWidth(88);
                                    buttonStrip3.setHeight(48);
                                    break;
                                case 3:
                                    buttonStrip4.setText("Strip " + numstrip + "\nLeds " + edittextLeds);
                                    buttonStrip4.setTextSize(10);
                                    buttonStrip4.setWidth(88);
                                    buttonStrip4.setHeight(48);
                                    break;
                                case 4:
                                    buttonStrip5.setText("Strip " + numstrip + "\nLeds " + edittextLeds);
                                    buttonStrip5.setTextSize(10);
                                    buttonStrip5.setWidth(88);
                                    buttonStrip5.setHeight(48);
                                    break;
                                case 5:
                                    buttonStrip6.setText("Strip " + numstrip + "\nLeds " + edittextLeds);
                                    buttonStrip6.setTextSize(10);
                                    buttonStrip6.setWidth(88);
                                    buttonStrip6.setHeight(48);
                                    break;
                                case 6:
                                    buttonStrip7.setText("Strip " + numstrip + "\nLeds " + edittextLeds);
                                    buttonStrip7.setTextSize(10);
                                    buttonStrip7.setWidth(88);
                                    buttonStrip7.setHeight(48);
                                    break;
                                case 7:
                                    buttonStrip8.setText("Strip " + numstrip + "\nLeds " + edittextLeds);
                                    buttonStrip8.setTextSize(10);
                                    buttonAddStrip.setEnabled(false);
                                    buttonStrip8.setWidth(88);
                                    buttonStrip8.setHeight(48);
                                    break;
                            }
                            tiras.add(tira);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    lightColor = Color.parseColor("#7fd9eb");
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(lightColor));
                }
            }
        });

        //pensar com agrupar els strips i comguardarlos en el firabase per mantenirlos al tancar l'app
        buttonAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnlegnthbyid();

                ArrayList<Tira> arrayListtiragroups = new ArrayList<>();
                ArrayList<String> selectedItems = new ArrayList<>();

                generatedGroupButtons();


                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(Show.this, R.style.myDialogSttrips));


                if (groupsArrayList.size() < 8) {

                    builder.setTitle("En el grup quines strips vols ajuntar?").setMultiChoiceItems(R.array.Strips, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which,
                                            boolean isChecked) {
                            if (isChecked) {
                                which = which + 1;
                                selectedItems.add(String.valueOf(which));
                            } else {
                                selectedItems.remove(String.valueOf(which));
                            }
                        }
                    })
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    if (selectedItems.size() > tiras.size()) {
                                        Toast.makeText(Show.this, "Hi ha mes Strips marcats dels que existeixen", Toast.LENGTH_SHORT).show();
                                    } else {
                                        for (int i = 0; i < selectedItems.size(); i++) {
                                            for (int j = 0; j < tiras.size(); j++) {
                                                if (selectedItems.get(i).equals(tiras.get(j).getNumtira())) {
                                                    Tira tira = new Tira(tiras.get(j).getNumtira(), tiras.get(j).getNumleds());
                                                    arrayListtiragroups.add(tira);
                                                }
                                            }
                                        }
                                        if (groupsArrayList.size() < 8) {
                                            for (int i = 0; i < selectedItems.size(); i++) {
                                                mDatabase.child("Groups").child("groupsnull").child("groups" + groupsArrayList.size()).push().setValue(arrayListtiragroups.get(i));
                                            }
                                            String stripsgroups = "";
                                            int groupsintid = groupsArrayList.size() + 1;

                                            switch (groupsArrayList.size()) {
                                                case 0:
                                                    for (int i = 0; i < arrayListtiragroups.size(); i++) {
                                                        stripsgroups = stripsgroups + "," + arrayListtiragroups.get(i).getNumtira();
                                                    }
                                                    buttonGroup1.setText("Group " + groupsintid + "\nS" + stripsgroups);
                                                    buttonGroup1.setTextSize(10);
                                                    buttonGroup1.setWidth(88);
                                                    buttonGroup1.setHeight(48);
                                                    break;
                                                case 1:
                                                    for (int i = 0; i < arrayListtiragroups.size(); i++) {
                                                        stripsgroups = stripsgroups + "," + arrayListtiragroups.get(i).getNumtira();
                                                    }
                                                    buttonGroup2.setText("Group " + groupsintid + "\nS" + stripsgroups);
                                                    buttonGroup2.setTextSize(10);
                                                    buttonGroup2.setWidth(88);
                                                    buttonGroup2.setHeight(48);
                                                    break;
                                                case 2:
                                                    for (int i = 0; i < arrayListtiragroups.size(); i++) {
                                                        stripsgroups = stripsgroups + "," + arrayListtiragroups.get(i).getNumtira();
                                                    }
                                                    buttonGroup3.setText("Group " + groupsintid + "\nS" + stripsgroups);
                                                    buttonGroup3.setTextSize(10);
                                                    buttonGroup3.setWidth(88);
                                                    buttonGroup3.setHeight(48);
                                                    break;
                                                case 3:
                                                    for (int i = 0; i < arrayListtiragroups.size(); i++) {
                                                        stripsgroups = stripsgroups + "," + arrayListtiragroups.get(i).getNumtira();
                                                    }
                                                    buttonGroup4.setText("Group " + groupsintid + "\nS" + stripsgroups);
                                                    buttonGroup4.setTextSize(10);
                                                    buttonGroup4.setWidth(88);
                                                    buttonGroup4.setHeight(48);
                                                    break;
                                                case 4:
                                                    for (int i = 0; i < arrayListtiragroups.size(); i++) {
                                                        stripsgroups = stripsgroups + "," + arrayListtiragroups.get(i).getNumtira();
                                                    }
                                                    buttonGroup5.setText("Group " + groupsintid + "\nS" + stripsgroups);
                                                    buttonGroup5.setTextSize(10);
                                                    buttonGroup5.setWidth(88);
                                                    buttonGroup5.setHeight(48);
                                                    break;
                                                case 5:
                                                    for (int i = 0; i < arrayListtiragroups.size(); i++) {
                                                        stripsgroups = stripsgroups + "," + arrayListtiragroups.get(i).getNumtira();
                                                    }
                                                    buttonGroup6.setText("Group " + groupsintid + "\nS" + stripsgroups);
                                                    buttonGroup6.setTextSize(10);
                                                    buttonGroup6.setWidth(88);
                                                    buttonGroup6.setHeight(48);
                                                    break;
                                                case 6:
                                                    for (int i = 0; i < arrayListtiragroups.size(); i++) {
                                                        stripsgroups = stripsgroups + "," + arrayListtiragroups.get(i).getNumtira();
                                                    }
                                                    buttonGroup7.setText("Group " + groupsintid + "\nS" + stripsgroups);
                                                    buttonGroup7.setTextSize(10);
                                                    buttonGroup7.setWidth(88);
                                                    buttonGroup7.setHeight(48);
                                                    break;
                                                case 7:
                                                    for (int i = 0; i < arrayListtiragroups.size(); i++) {
                                                        stripsgroups = stripsgroups + "," + arrayListtiragroups.get(i).getNumtira();
                                                    }
                                                    buttonGroup8.setText("Group " + groupsintid + "\nAll Strips");
                                                    buttonGroup8.setTextSize(10);
                                                    buttonGroup8.setWidth(88);
                                                    buttonGroup8.setHeight(48);
                                                    buttonAddGroup.setEnabled(false);
                                                    break;
                                            }
                                        } else {
                                            Toast.makeText(Show.this, "Esta ple", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });


                    int lightColor = Color.parseColor("#FFFFFF");

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    lightColor = Color.parseColor("#ffc88e");
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(lightColor));
                } else {
                    Toast.makeText(Show.this, "Esta ple", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void returnlegnthbyid() {
        mDatabase.child("Groups").child("groupsnull").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    groupsArrayList.clear();

                    for (DataSnapshot ds : snapshot.getChildren()) {
                        groupsArrayList.add(ds.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void generatedGroupButtons() {
        System.out.println(groupsArrayList.size());
        for (int i = 0; i < groupsArrayList.size(); i++) {
            switch (i) {
                case 0:

                    buttonGroup1.setText("Group " + i);
                    buttonGroup1.setTextSize(10);
                    buttonGroup1.setWidth(88);
                    buttonGroup1.setHeight(48);
                    break;
                case 1:

                    buttonGroup2.setText("Group " + i);
                    buttonGroup2.setTextSize(10);
                    buttonGroup2.setWidth(88);
                    buttonGroup2.setHeight(48);

                    break;
                case 2:

                    buttonGroup3.setText("Group " + i);
                    buttonGroup3.setTextSize(10);
                    buttonGroup3.setWidth(88);
                    buttonGroup3.setHeight(48);
                    break;
                case 3:

                    buttonGroup4.setText("Group " + i);
                    buttonGroup4.setTextSize(10);
                    buttonGroup4.setWidth(88);
                    buttonGroup4.setHeight(48);
                    break;
                case 4:

                    buttonGroup5.setText("Group " + i);
                    buttonGroup5.setTextSize(10);
                    buttonGroup5.setWidth(88);
                    buttonGroup5.setHeight(48);
                    break;
                case 5:

                    buttonGroup6.setText("Group " + i);
                    buttonGroup6.setTextSize(10);
                    buttonGroup6.setWidth(88);
                    buttonGroup6.setHeight(48);
                    break;
                case 6:

                    buttonGroup7.setText("Group " + i);

                    buttonGroup7.setTextSize(10);
                    buttonGroup7.setWidth(88);
                    buttonGroup7.setHeight(48);
                    break;
                case 7:

                    buttonGroup8.setText("Group " + i);

                    buttonGroup8.setTextSize(10);
                    buttonGroup8.setWidth(88);
                    buttonGroup8.setHeight(48);
                    buttonAddGroup.setEnabled(false);

                    break;
            }
        }
    }

    private void stripsButtons() {
        Log.d("MSG", String.valueOf(tiras.size()));
        for (int i = 0; i < tiras.size(); i++) {
            switch (i) {
                case 0:
                    buttonStrip1.setText("Strip " + tiras.get(i).getNumtira() + "\nLeds " + tiras.get(i).getNumleds());
                    buttonStrip1.setTextSize(10);
                    buttonStrip1.setWidth(88);
                    buttonStrip1.setHeight(48);
                    break;
                case 1:
                    buttonStrip2.setText("Strip " + tiras.get(i).getNumtira() + "\nLeds " + tiras.get(i).getNumleds());
                    buttonStrip2.setTextSize(10);
                    buttonStrip2.setWidth(88);
                    buttonStrip2.setHeight(48);
                    break;
                case 2:
                    buttonStrip3.setText("Strip " + tiras.get(i).getNumtira() + "\nLeds " + tiras.get(i).getNumleds());
                    buttonStrip3.setTextSize(10);
                    buttonStrip3.setWidth(88);
                    buttonStrip3.setHeight(48);
                    break;
                case 3:
                    buttonStrip4.setText("Strip " + tiras.get(i).getNumtira() + "\nLeds " + tiras.get(i).getNumleds());
                    buttonStrip4.setTextSize(10);
                    buttonStrip4.setWidth(88);
                    buttonStrip4.setHeight(48);
                    break;
                case 4:
                    buttonStrip5.setText("Strip " + tiras.get(i).getNumtira() + "\nLeds " + tiras.get(i).getNumleds());
                    buttonStrip5.setTextSize(10);
                    buttonStrip5.setWidth(88);
                    buttonStrip5.setHeight(48);
                    break;
                case 5:
                    buttonStrip6.setText("Strip " + tiras.get(i).getNumtira() + "\nLeds " + tiras.get(i).getNumleds());
                    buttonStrip6.setTextSize(10);
                    buttonStrip6.setWidth(88);
                    buttonStrip6.setHeight(48);
                    break;
                case 6:
                    buttonStrip7.setText("Strip " + tiras.get(i).getNumtira() + "\nLeds " + tiras.get(i).getNumleds());
                    buttonStrip7.setTextSize(10);
                    buttonStrip7.setWidth(88);
                    buttonStrip7.setHeight(48);
                    break;
                case 7:
                    buttonStrip8.setText("Strip " + tiras.get(i).getNumtira() + "\nLeds " + tiras.get(i).getNumleds());
                    buttonStrip8.setTextSize(10);
                    buttonStrip8.setWidth(88);
                    buttonStrip8.setHeight(48);
                    buttonAddStrip.setEnabled(false);

                    break;
            }
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuClosePP:
                Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();
                registry.stopPushing();
                service.shutdown();

                break;

            case R.id.menuResetPP:
                Toast.makeText(this, item.getItemId(), Toast.LENGTH_SHORT).show();
                //TODO
                buttonStrip1.setText("");
                buttonStrip2.setText("");
                buttonStrip3.setText("");
                buttonStrip4.setText("");
                buttonStrip5.setText("");
                buttonStrip6.setText("");
                buttonStrip7.setText("");
                buttonStrip8.setText("");
                buttonGroup1.setText("");
                buttonGroup2.setText("");
                buttonGroup3.setText("");
                buttonGroup4.setText("");
                buttonGroup5.setText("");
                buttonGroup6.setText("");
                buttonGroup7.setText("");
                buttonGroup8.setText("");
                buttonAddStrip.setEnabled(true);
                buttonAddGroup.setEnabled(true);
                mDatabase.child("Groups").removeValue();
                mDatabase.child("Strips").removeValue();
                registry.stopPushing();
                service.shutdown();

                break;


            case R.id.menuRepError:
                Toast.makeText(this, item.getItemId(), Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ReportErrors.class);
                startActivity(intent);
                break;

            case R.id.menuLogOut:
                Toast.makeText(this, item.getItemId(), Toast.LENGTH_SHORT).show();
                intent = new Intent(this, LogIn.class);
                editor.remove("myID").apply();
                editor.remove("myUserName").apply();
                editor.remove("myEmail").apply();
                editor.remove("myPass").apply();
                editor.remove("myCountry").apply();
                editor.commit();
                startActivity(intent);
                finish();
                break;


            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void returnLength() {
        mDatabase.child("Strips").child("Stripsnull").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    tiras.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Tira tiraarray = new Tira(ds.child("numtira").getValue().toString(), ds.child("numleds").getValue().toString());
                        tiras.add(tiraarray);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

}