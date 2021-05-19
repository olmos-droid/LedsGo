package com.example.merda;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForgotPass extends AppCompatActivity {

    private EditText  editTextNewPassword, editTextConfirmNewPass;
    private Button buttonSavePassword;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        editTextNewPassword = findViewById(R.id.editText_ForgotPass_NewPass);
        editTextConfirmNewPass = findViewById(R.id.editText_ForgotPass_ConfirmPass);
        buttonSavePassword = (Button) findViewById(R.id.button_ForgotPass_Save);
        mDatabase= FirebaseDatabase.getInstance().getReference();


        buttonSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextConfirmNewPass.getText().toString().isEmpty()&&editTextNewPassword.getText().toString().isEmpty()) {
                    Toast.makeText(com.example.ledsgo.ForgotPass.this, "esta tot buit", Toast.LENGTH_SHORT).show();
                } else {
                    if (editTextConfirmNewPass.getText().toString().equals(editTextNewPassword.getText().toString())) {
                        String id=getIntent().getStringExtra("id");
                        String name=getIntent().getStringExtra("name");
                        String email=getIntent().getStringExtra("email");
                        String country=getIntent().getStringExtra("country");
                        com.example.ledsgo.Users users=new com.example.ledsgo.Users(name,email,editTextNewPassword.getText().toString(),country);
                        mDatabase.child("Usuarios").child(id).setValue(users);

                        startActivity(new Intent(getApplicationContext(), com.example.ledsgo.LogIn.class));
                    }


                }

            }
        });
    }
}
