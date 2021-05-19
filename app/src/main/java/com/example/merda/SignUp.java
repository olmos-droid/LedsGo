package com.example.merda;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword, editTextComprobarPass,editTextComprobarEmail;
    private TextView textViewComprobarEmail;
    private Spinner spinnerCountry;
    private Button buttonSignUp, buttonComprobar, buttonReenviar;
    private String stringname, stringEmail, stringPassword, stringCountry,stringComprobarpass;
    private DatabaseReference mDatabase;
    private ArrayList<com.example.ledsgo.Users> arraylistUserBBDD = new ArrayList<>();
    private ArrayList<String> arraylistUserComprobarEmail = new ArrayList<>();
    private String stringGeneratedString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextComprobarEmail = findViewById(R.id.editText_SignUp_GeneratedString);
        textViewComprobarEmail = findViewById(R.id.textView_SingUp_ComprobarEmail);
        buttonComprobar = findViewById(R.id.button_SignUp_Comprovar);
        buttonReenviar = findViewById(R.id.button_SignUp_Reenviar);

        editTextName = findViewById(R.id.editText_SignUp_NickName);
        editTextEmail = findViewById(R.id.editText_SignUp_EmailAddress);
        editTextPassword = findViewById(R.id.editText_SignUp_Password);
        spinnerCountry = findViewById(R.id.Spinner_SignUp_Paises);
        buttonSignUp = findViewById(R.id.button_Signin_CreateUser);
        editTextComprobarPass = findViewById(R.id.editText_SignUp_ConfirmPassword);
        mDatabase = FirebaseDatabase.getInstance().getReference();







        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Paises, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(arrayAdapter);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stringCountry = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String email = ds.child("email").getValue().toString();


                        arraylistUserComprobarEmail.add(email);
                    }
                    Toast.makeText(com.example.ledsgo.SignUp.this, "datachanged", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(com.example.ledsgo.SignUp.this);
                stringname = String.valueOf(editTextName.getText());
                stringEmail = String.valueOf(editTextEmail.getText());
                stringPassword = String.valueOf(editTextPassword.getText());
                stringComprobarpass= String.valueOf(editTextComprobarPass.getText());

                String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                boolean validuser = isValid(stringEmail, regex);


                if (!(stringEmail.isEmpty() || stringname.isEmpty() || stringPassword.isEmpty() || stringCountry.isEmpty())) {

                    if (validuser == true && stringPassword.length() > 4 && stringPassword.equals(stringComprobarpass)) {


                        boolean emailequals = true;

                        for (int i = 0; i < arraylistUserComprobarEmail.size(); i++) {
                            if (arraylistUserComprobarEmail.get(i).equals(stringEmail)) {
                                emailequals = false;
                            }
                        }


                        if (emailequals) {

                            if (stringCountry.equals("country")) {
                                Toast.makeText(com.example.ledsgo.SignUp.this, "Selecciona un pais", Toast.LENGTH_SHORT).show();
                            } else {


                                editTextComprobarEmail.setAlpha(1);
                                editTextComprobarEmail.setEnabled(true);
                                textViewComprobarEmail.setAlpha(1);
                                buttonComprobar.setAlpha(1);
                                buttonComprobar.setEnabled(true);
                                buttonReenviar.setAlpha(1);
                                buttonReenviar.setEnabled(true);

                                Random random = new Random();

                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                    stringGeneratedString = random.ints(97, 122 + 1)
                                            .limit(9)
                                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                            .toString();
                                }

                                System.out.println("Aquesta es la clau " + stringGeneratedString);
                            new SendEmail("Ledsgoescolatreball@gmail.com", "ledsgoescola").execute(
                                    new SendEmail.Mail("Ledsgoescolatreball@gmail.com", stringEmail, "Completar registro", "Codigo aleatorio generado para comprobar que el email existe: " + stringGeneratedString + "\n Introducelo en la aplicacion para completar el registro. \n\n Equipo de LedsGo")
                            );


//                                Users user = new Users(stringname, stringEmail, stringPassword, stringCountry);
//
//                                arraylistUserBBDD.add(user);
//                                mDatabase.child("Usuarios").push().setValue(user);


//                                Intent intent = new Intent(SignUp.this, LogIn.class);
//                                startActivity(intent);
                            }
                        } else {

                            dlgAlert.setMessage("Aquest email ja existeix");

                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();


                        }
                    } else {


                        dlgAlert.setMessage("Correo o Contraseña incorrecta.");

                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();


                    }
                } else {
                    dlgAlert.setMessage("Omple tots els camps.\nPer seguir amb el registre");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                }

            }
        });

        buttonComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Aixo si que entra");
                if (editTextComprobarEmail.getText().toString().equals(stringGeneratedString)) {
                    com.example.ledsgo.Users user = new com.example.ledsgo.Users(stringname, stringEmail, stringPassword, stringCountry);

                    arraylistUserBBDD.add(user);
                    mDatabase.child("Usuarios").push().setValue(user);


                    Intent intent = new Intent(com.example.ledsgo.SignUp.this, com.example.ledsgo.LogIn.class);
                    startActivity(intent);
                }
            }
        });
        buttonReenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    stringGeneratedString = random.ints(97, 122 + 1)
                            .limit(9)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();
                }
           System.out.println("Aquesta es la clau " + stringGeneratedString);
                            new SendEmail("Ledsgoescolatreball@gmail.com", "ledsgoescola").execute(
                                    new SendEmail.Mail("Ledsgoescolatreball@gmail.com", stringEmail, "Completar registro", "Codigo aleatorio generado para comprobar que el email existe: " + stringGeneratedString + "\n Introducelo en la aplicacion para completar el registro. \n\n Equipo de LedsGo")
                            );

            }
        });
    }

    public static boolean isValid(String password, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }


}
