package com.example.merda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private TextView textViewForgotPassword;
    private Button buttonLogin, buttonRegister;
    private String stringEmail, stringPassword, stringName, stringid, stringCountry;
    private DatabaseReference mDatabase;
    private int pos;
    private ArrayList<com.example.ledsgo.Users> arraylistUser = new ArrayList<>();
    private ArrayList<String> arraylistID = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        com.example.ledsgo.Users user = leerValor(getApplicationContext());
        if (!(user.getEmail() == null)) {
            Intent intent = new Intent(getApplicationContext(), com.example.ledsgo.Show.class);
            startActivity(intent);
        }

        editTextEmail = findViewById(R.id.editText_LogIn_Email);
        editTextPassword = findViewById(R.id.editText_LogIn_Password);
        textViewForgotPassword = findViewById(R.id.textView_Login_ForgetPassword);
        buttonLogin = findViewById(R.id.button_LogIn_SignIn);
        buttonRegister = findViewById(R.id.button_Login_CreateUser);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.ledsgo.LogIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        String id = ds.getKey();
                        arraylistID.add(id);
                        com.example.ledsgo.Users user = new com.example.ledsgo.Users();
                        user.setEmail(ds.child("email").getValue().toString());
                        user.setPassword(ds.child("password").getValue().toString());
                        user.setName(ds.child("name").getValue().toString());
                        user.setCountry(ds.child("country").getValue().toString());
                        arraylistUser.add(user);

                    }
                    Toast.makeText(com.example.ledsgo.LogIn.this, "datachanged", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringEmail = editTextEmail.getText().toString();
                stringPassword = editTextPassword.getText().toString();
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(com.example.ledsgo.LogIn.this);


                String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                boolean validuser = isValid(stringEmail, regex);


                if (!(stringEmail.isEmpty() || stringPassword.isEmpty())) {
                    if (validuser == true && stringPassword.length() > 4) {


                        if (ComprobarEmail(stringEmail, stringPassword)) {
                            userSharedPreferences();
                            Intent intent = new Intent(com.example.ledsgo.LogIn.this, com.example.ledsgo.Show.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(com.example.ledsgo.LogIn.this, "Email o Pass incorrect", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        dlgAlert.setMessage("Correo o Contraseña incorrecta.\nRecuerda que la contraseña requiere de 1 minuscula, 1 mayuscula y 1 numero");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                    }
                } else {
                    dlgAlert.setMessage("Omple tots els camps.\nPer seguir amb el registre");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }
            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComprobarEmail(editTextEmail.getText().toString(), editTextPassword.getText().toString());

                if (!(editTextEmail.getText().toString().isEmpty())) {

                    Intent intent = new Intent(getApplicationContext(), ForgotPass.class);
                    intent.putExtra("id", arraylistID.get(pos));
                    intent.putExtra("email", arraylistUser.get(pos).getEmail());
                    intent.putExtra("name", arraylistUser.get(pos).getName());
                    intent.putExtra("country", arraylistUser.get(pos).getCountry());
                    intent.putExtra("pass", arraylistUser.get(pos).getPassword());

                    startActivity(intent);

                } else {
                    Toast.makeText(com.example.ledsgo.LogIn.this, "Introdueix el email siusplau", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public boolean ComprobarEmail(String emaillogin, String passlogin) {

        boolean exist = false;

        for (int i = 0; i < arraylistUser.size(); i++) {
            if (emaillogin.equals(arraylistUser.get(i).getEmail())) {
                pos = i;
            }

            if (emaillogin.equals(arraylistUser.get(i).getEmail()) && passlogin.equals(arraylistUser.get(i).getPassword())) {
                pos = i;
                exist = true;
            }
        }

        return exist;

    }

    public static boolean isValid(String password, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public void userSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("myID", arraylistID.get(pos));
        editor.putString("myUserName", arraylistUser.get(pos).getName());
        editor.putString("myEmail", arraylistUser.get(pos).getEmail());
        editor.putString("myPass", arraylistUser.get(pos).getPassword());
        editor.putString("myCountry", arraylistUser.get(pos).getCountry());

        if (sharedPreferences.getString("image", null) == null) {
            editor.putString("image", String.valueOf(R.drawable.ic_baseline_person_24));
        }

        editor.commit();

    }


    public com.example.ledsgo.Users leerValor(Context context) {

        com.example.ledsgo.Users users = new com.example.ledsgo.Users();

        SharedPreferences preferences = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        users.setEmail(preferences.getString("myEmail", null));
        users.setPassword(preferences.getString("myPass", null));

        return users;
    }

}



