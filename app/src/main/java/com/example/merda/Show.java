package com.example.merda;

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

import java.util.ArrayList;

public class Show extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Show";

    private FrameLayout mMainFrame;
    private GeneralFragment generalFragment;
    private GroupsFragment groupsFragment;
    private StripsFragment stripsFragment;
    private Button buttonGeneral, buttonStrips, buttonGroups, buttonAddStrip,buttonAddGroup;
    private Button buttonStrip1,buttonStrip2,buttonStrip3,buttonStrip4,buttonStrip5,buttonStrip6,buttonStrip7,buttonStrip8;
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
    private ArrayList<Tira> tiras=new ArrayList<>();
    private int cont;
    private String id="ASDASD";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        buttonStrip1=findViewById(R.id.button_show_strip1);
        buttonStrip2=findViewById(R.id.button_show_strip2);
        buttonStrip3=findViewById(R.id.button_show_strip3);
        buttonStrip4=findViewById(R.id.button_show_strip4);
        buttonStrip5=findViewById(R.id.button_show_strip5);
        buttonStrip6=findViewById(R.id.button_show_strip6);
        buttonStrip7=findViewById(R.id.button_show_strip7);
        buttonStrip8=findViewById(R.id.button_show_strip8);


        mMainFrame = findViewById(R.id.main_frame);
        generalFragment = new GeneralFragment();
        groupsFragment = new GroupsFragment();
        stripsFragment = new StripsFragment();

        setFragment(generalFragment);

        buttonGeneral = findViewById(R.id.Button_Show_GeneralFrag);
        buttonStrips = findViewById(R.id.Button_Show_StripsFrag);
        buttonGroups = findViewById(R.id.Button_Show_GroupsFrag);
        drawerLayout = findViewById(R.id.drawer_layout);
        buttonAddStrip=findViewById(R.id.button_show_strip_add);
        buttonAddGroup=findViewById(R.id.button_show_group_add);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.mytoolbar);
        View headerView = navigationView.getHeaderView(0);
        verticalLayoutPerfil=headerView.findViewById(R.id.LinearLayout_Header_Perfil);
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


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(com.example.ledsgo.Show.this,
                drawerLayout,
                toolbar,
                R.string.open,
                R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_view);

//        List<Strip> strips = registry.getStrips();
//        prepareExitHandler(registry);
        buttonStrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(stripsFragment);
            }
        });
        buttonGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(generalFragment);
            }
        });
        buttonGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


        buttonAddStrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnLength();
                String user=sharedPreferences.getString("id",null);
                String numstrip= returnLength();
                stripsButtons(); //aconseguir que ho fasi en el onCreate no en el setOnClickListener, pero no detecta el tiras.size() perque surt valor a 0 fora del setOnClikListener

                if (!(tiras.size()>7)){



                AlertDialog.Builder builder=new AlertDialog.Builder(new ContextThemeWrapper(com.example.ledsgo.Show.this,R.style.myDialogSttrips));
                builder.setTitle("Quants Leds conte la Strip?");

                int lightColor = Color.parseColor("#FFFFFF");

                final EditText editTextproba=new EditText(getApplicationContext());
                editTextproba.setTextColor(lightColor);
                editTextproba.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(editTextproba);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         edittextLeds=editTextproba.getText().toString();
                        Tira tira=new Tira(numstrip,edittextLeds);
                        mDatabase.child("Strips").child("Strips"+user).push().setValue(tira);
                        switch (tiras.size()){
                            case 0:
                                buttonStrip1.setText("Strip "+numstrip+"\nLeds "+edittextLeds);
                                break;
                            case 1:
                                buttonStrip2.setText("Strip "+numstrip+"\nLeds "+edittextLeds);

                                break;
                            case 2:
                                buttonStrip3.setText("Strip "+numstrip+"\nLeds "+edittextLeds);

                                break;
                            case 3:
                                buttonStrip4.setText("Strip "+numstrip+"\nLeds "+edittextLeds);

                                break;
                            case 4:
                                buttonStrip5.setText("Strip "+numstrip+"\nLeds "+edittextLeds);

                                break;
                            case 5:
                                buttonStrip6.setText("Strip "+numstrip+"\nLeds "+edittextLeds);

                                break;
                            case 6:
                                buttonStrip7.setText("Strip "+numstrip+"\nLeds "+edittextLeds);

                                break;
                            case 7:
                                buttonStrip8.setText("Strip "+numstrip+"\nLeds "+edittextLeds);
                                buttonAddStrip.setEnabled(false);

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
                AlertDialog.Builder builder=new AlertDialog.Builder(new ContextThemeWrapper(com.example.ledsgo.Show.this,R.style.myDialogSttrips));
                builder.setTitle("Quants Strips conte conte el grup?");

                int lightColor = Color.parseColor("#FFFFFF");

                final EditText editTextproba=new EditText(getApplicationContext());
                editTextproba.setWidth(30);
                editTextproba.setHeight(10);
                editTextproba.setTextColor(lightColor);
                editTextproba.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(editTextproba);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String string=editTextproba.getText().toString();
                        Toast.makeText(com.example.ledsgo.Show.this, string, Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                lightColor = Color.parseColor("#ffc88e");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(lightColor));
            }
        });

    }

    private void stripsButtons(){

        for (int i = 0; i <tiras.size() ; i++) {
            switch (i){
                case 0:
                    buttonStrip1.setText("Strip "+tiras.get(i).getNumtira()+"\nLeds "+edittextLeds);
                    break;
                case 1:
                    buttonStrip2.setText("Strip "+tiras.get(i).getNumtira()+"\nLeds "+edittextLeds);

                    break;
                case 2:
                    buttonStrip3.setText("Strip "+tiras.get(i).getNumtira()+"\nLeds "+edittextLeds);

                    break;
                case 3:
                    buttonStrip4.setText("Strip "+tiras.get(i).getNumtira()+"\nLeds "+edittextLeds);

                    break;
                case 4:
                    buttonStrip5.setText("Strip "+tiras.get(i).getNumtira()+"\nLeds "+edittextLeds);

                    break;
                case 5:
                    buttonStrip6.setText("Strip "+tiras.get(i).getNumtira()+"\nLeds "+edittextLeds);

                    break;
                case 6:
                    buttonStrip7.setText("Strip "+tiras.get(i).getNumtira()+"\nLeds "+edittextLeds);

                    break;
                case 7:
                    buttonStrip8.setText("Strip "+tiras.get(i).getNumtira()+"\nLeds "+edittextLeds);
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

                //TODO
                //Apagar Pixel Pusher
                break;

            case R.id.menuResetPP:
                Toast.makeText(this, item.getItemId(), Toast.LENGTH_SHORT).show();
                //TODO
                //Reiniciar Pixel Pusher
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
    private String returnLength( ){
        mDatabase.child("Strips").child("Stripsnull").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    tiras.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {

                        Tira tiraarray=new Tira(ds.child("numtira").getValue().toString(),ds.child("numleds").getValue().toString());
                        System.out.println(tiraarray);
                        tiras.add(tiraarray);
                    }
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cont=0;

        for (int i = 0; i <tiras.size() ; i++) {
            System.out.println(tiras.get(i));
            cont++;
        }
        String contfinal= String.valueOf(cont+1);
        return contfinal;
    }
}