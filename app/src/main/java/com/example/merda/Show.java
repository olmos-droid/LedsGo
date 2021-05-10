package com.example.merda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Show extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView textViewUser;
    private ImageView imageViewUser;
    private DatabaseReference mDatabase;
    private String topefirst, topelast;
    private String totalName;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Bitmap bmp;


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.mytoolbar);
        View headerView = navigationView.getHeaderView(0);
        textViewUser = (TextView) headerView.findViewById(R.id.textView_Header_username);
        imageViewUser = headerView.findViewById(R.id.imageView_Header_User);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sharedPreferences=getSharedPreferences("USER", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        Bundle extras = getIntent().getExtras();


        if (!(extras == null)) {

            bmp = (Bitmap) extras.getParcelable("imagebitmap");
            String bmp2 = extras.getString("user");

            textViewUser.setText(bmp2);
            imageViewUser.setImageBitmap(bmp);
        }
        String stringSharedPrefName = sharedPreferences.getString("myUserName",null);

        if (stringSharedPrefName != null) {

            stringSharedPrefName.toUpperCase();

            topefirst = stringSharedPrefName.substring(0, 1).toUpperCase();
            topelast = stringSharedPrefName.substring(1, stringSharedPrefName.length());
            totalName = topefirst + topelast;

            textViewUser.setText(totalName);
            textViewUser.setTextSize(16);
            imageViewUser.setImageResource(R.drawable.ic_baseline_person_24);
            editor.putString("myUserName",totalName);
            editor.commit();
            byte[] imageAsBytes = Base64.decode(sharedPreferences.getString("image",null).getBytes(), Base64.DEFAULT);
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

        textViewUser.setOnClickListener(new View.OnClickListener() {
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

            case R.id.menuPatrones:
                Toast.makeText(this, item.getItemId(), Toast.LENGTH_SHORT).show();
                intent = new Intent(Show.this, Patrones.class);
                startActivity(intent);

                break;

            case R.id.menuRepError:
                Toast.makeText(this, item.getItemId(), Toast.LENGTH_SHORT).show();
                intent = new Intent(this, ReportErrors.class);
                startActivity(intent);
                break;

            case R.id.menuLogOut:
                Toast.makeText(this, item.getItemId(), Toast.LENGTH_SHORT).show();
                intent = new Intent(this, LogIn.class);
                editor.clear().apply();
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
}