package com.example.merda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Perfil extends AppCompatActivity {

    private TextView textViewUserName;
    private EditText editTextUserName, editTextEmail, editTextPass;
    private Button buttonSaveUser, buttonSaveEmail, buttonSavePass;
    private DatabaseReference mDatabase;
    private ArrayList<com.example.ledsgo.Users> arraylistUser = new ArrayList<>();
    private String datos,email,name,country,pass,ID;
    private ImageView imageViewUser;
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        textViewUserName = findViewById(R.id.textView_Perfil_UserName);
        editTextUserName = findViewById(R.id.editText_Perfil_UserName);
        editTextEmail = findViewById(R.id.editText_Perfil_Email);
        editTextPass = findViewById(R.id.editText_Perfil_Pass);
        buttonSaveUser = findViewById(R.id.button_Perfil_username);
        buttonSaveEmail = findViewById(R.id.button_Perfil_Email);
        buttonSavePass = findViewById(R.id.button_Perfil_Pass);
        imageViewUser = findViewById(R.id.imageView_Perfil_fotoPerfil);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sharedPreferences=getSharedPreferences("USER",getApplicationContext().MODE_PRIVATE);
        editor=sharedPreferences.edit();

        if (sharedPreferences.getString("myID",null)!=null) {
             email = sharedPreferences.getString("myEmail", null);
             name = sharedPreferences.getString("myUserName", null);
             ID = sharedPreferences.getString("myID", null);
             pass = sharedPreferences.getString("myPass", null);
             country = sharedPreferences.getString("myCountry", null);
        }

        Bundle extras = getIntent().getExtras();


        if (!(extras == null)) {

           Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");

            imageViewUser.setImageBitmap(bmp);
        }

        textViewUserName.setText(name);
        editTextUserName.setText(name);
        editTextEmail.setText(email);
        editTextPass.setText(pass);


        buttonSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos = editTextUserName.getText().toString();
                com.example.ledsgo.Users users = new com.example.ledsgo.Users();
                users.setName(datos);
                users.setEmail(editTextEmail.getText().toString());
                users.setPassword(editTextPass.getText().toString());
                users.setCountry(country);
                System.out.println(ID+"ASdasdads a das adsasd sa sa ");
                mDatabase.child("Usuarios").child(ID).setValue(users);
                textViewUserName.setText(editTextUserName.getText());
                editor.putString("myUserName",datos);
                editor.commit();
            }
        });
        buttonSaveEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


                datos = editTextEmail.getText().toString();
                boolean validuser = isValid(datos, regex);
                if (validuser == true) {
                    com.example.ledsgo.Users users = new com.example.ledsgo.Users();
                    users.setName(editTextUserName.getText().toString());
                    users.setEmail(datos);
                    users.setPassword(editTextPass.getText().toString());
                    users.setCountry(country);
                    mDatabase.child("Usuarios").child(ID).setValue(users);
                    textViewUserName.setText(editTextUserName.getText());
                    editor.putString("myEmail",datos);
                    editor.commit();

                }
            }
        });
        buttonSavePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datos = editTextPass.getText().toString();
                if (datos.length() > 4) {
                    com.example.ledsgo.Users users = new com.example.ledsgo.Users();
                    users.setName(editTextUserName.getText().toString());
                    users.setEmail(editTextEmail.getText().toString());
                    users.setPassword(datos);
                    users.setCountry(country);
                    mDatabase.child("Usuarios").child(ID).setValue(users);
                    textViewUserName.setText(editTextUserName.getText());
                    editor.putString("myPass",datos);
                    editor.commit();



                }
            }
        });
        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        imageViewUser.buildDrawingCache();
        Bitmap image=imageViewUser.getDrawingCache();
        Bundle extras = new Bundle();
        Bundle extras2 = new Bundle();
        extras.putParcelable("imagebitmap", image);
        String stringUserNameUpdate=editTextUserName.getText().toString();
        extras2.putString("user",stringUserNameUpdate);
        Intent intent= new Intent(getApplicationContext(), com.example.ledsgo.Show.class);
        intent.putExtras(extras);
        intent.putExtras(extras2);
        startActivity(intent);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String encoded = Base64.encodeToString(b, Base64.DEFAULT);
        editor.putString("image",encoded);
        editor.commit();

    }

    public static boolean isValid(String password, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageViewUser.setImageURI(imageUri);
        }
    }
}