package com.example.merda;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ReportErrors extends AppCompatActivity {

    private EditText editTextEmail, editTextError;
    private Button buttonSendError;
    private String stringEmail, stringReport;
    private DatabaseReference mDatabase;
    private ArrayList<String> arraylistError = new ArrayList<>();
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_errors);

        editTextEmail = findViewById(R.id.editText_Error_Email);
        editTextError = findViewById(R.id.editText_Errors_RepError);
        buttonSendError = findViewById(R.id.button_Errors_SendError);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sharedPreferences=getSharedPreferences("USER", Context.MODE_PRIVATE);

        if (!(sharedPreferences.getString("myEmail",null).equals(null))) {
            editTextEmail.setText(sharedPreferences.getString("myEmail",null));
        }

        mDatabase.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    for (DataSnapshot ds : snapshot.getChildren()) {

                        stringEmail = ds.child("email").getValue().toString();
                        arraylistError.add(stringEmail);


                    }
                    Toast.makeText(getApplicationContext(), "datachanged", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        buttonSendError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(com.example.ledsgo.ReportErrors.this);

                stringEmail = editTextEmail.getText().toString();
                stringReport = editTextError.getText().toString();
               com.example.ledsgo.Errors error = new com.example.ledsgo.Errors(stringEmail, stringReport);


                if (ComprobarEmail(stringEmail)) {
                    Toast.makeText(com.example.ledsgo.ReportErrors.this, "Error enviat", Toast.LENGTH_SHORT).show();
                    mDatabase.child("Report_errors").push().setValue(error);
                    new SendEmail("Ledsgoescolatreball@gmail.com", "ledsgoescola").execute(
                            new SendEmail.Mail("Ledsgoescolatreball@gmail.com", "Ledsgoescolatreball@gmail.com", "Reportar error", "El usuario: "+stringEmail+" quiere reportar un error.\nError: "+stringReport)
                    );

                } else {
                    dlgAlert.setMessage("El correo no existe");

                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();

                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                }

            }
        });


    }

    public boolean ComprobarEmail(String emaillogin) {

        boolean exist = false;

        for (int i = 0; i < arraylistError.size(); i++) {

            if (emaillogin.equals(arraylistError.get(i))) {

                exist = true;

            }

        }
        return exist;
    }
}
