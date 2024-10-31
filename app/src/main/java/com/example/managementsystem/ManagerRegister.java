package com.example.managementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ManagerRegister extends AppCompatActivity {
    private EditText editTextEmail, editTextname;
    private TextView textView;
    private EditText editTextPassword;
    private Button reg;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_register);
        editTextEmail = findViewById(R.id.Manageremailreg);
        editTextPassword = findViewById(R.id.ManagerregPass);
        reg = findViewById(R.id.ManagerRegBtn);
        textView = findViewById(R.id.ManagerLoginText);
        editTextname = findViewById(R.id.ManagerName);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String name = editTextname.getText().toString();

                if (TextUtils.isEmpty(email)){
                    editTextEmail.setError("email is required!");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    editTextPassword.setError("Password is required!");
                    return;
                }


                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ManagerRegister.this, "User Created!", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(), UserProfilePage.class));
                            UserId = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Manager").document(UserId);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Fname",name);
                            user.put("email",email);
                            user.put("password",password);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG","User created"+UserId);
                                }
                            });
                            Intent intent = new Intent (getApplicationContext(),ManagerLogin.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(ManagerRegister.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}