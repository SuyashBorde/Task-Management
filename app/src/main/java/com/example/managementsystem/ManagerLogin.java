package com.example.managementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManagerLogin extends AppCompatActivity {

    FirebaseAuth fAuth;
    private TextView textView;
    private EditText emailid,pass;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);
        emailid = findViewById(R.id.ManagerLoginEmail);
        pass = findViewById(R.id.ManagerLoginPass);
        login = findViewById(R.id.ManagerLoginBtn);
        textView = findViewById(R.id.ManagerRegisterText);
        fAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailid.getText().toString();
                String password = pass.getText().toString();

                if (TextUtils.isEmpty(email)){
                    emailid.setError("email is required!");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    pass.setError("Password is required!");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( task -> {
                            if (task.isSuccessful()) {
                                // Employee login successful
                                FirebaseUser user = fAuth.getCurrentUser();
                                Toast.makeText(ManagerLogin.this, "Successfully Logged inn!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), SaveTaskActivity.class));
                                finish();
                                // Redirect to the employee dashboard or desired page
                            } else {
                                // Employee login failed
                                Toast.makeText(ManagerLogin.this, "Authentication Failed!!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManagerRegister.class);
                startActivity(intent);
            }
        });
    }
}