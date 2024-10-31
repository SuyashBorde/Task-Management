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

public class EmpLoginActivity extends AppCompatActivity {

    FirebaseAuth fauth;
    TextView textView;
    EditText emailid,pass;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_login);

        emailid = findViewById(R.id.EmployeeEmail);
        pass = findViewById(R.id.EmployeePassword);
        login = findViewById(R.id.EmployeeLoginBtn);
        textView = findViewById(R.id.EmployeeRegisterText);
        fauth = FirebaseAuth.getInstance();

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

                fauth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( task -> {
                            if (task.isSuccessful()) {
                                // Employee login successful
                                FirebaseUser user = fauth.getCurrentUser();
                                Toast.makeText(EmpLoginActivity.this, "Successfully Logged inn!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), EmpTaskList.class));
                                finish();
                                // Redirect to the employee dashboard or desired page
                            } else {
                                // Employee login failed
                                Toast.makeText(EmpLoginActivity.this, "Authentication Failed!!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EmpRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}