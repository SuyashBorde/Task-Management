package com.example.managementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;

public class editDataActivity extends AppCompatActivity {

    private static final String TAG = "editDataActivity";

    private Button btnSave,btnDelete;
    private EditText editable_item;

    DatabaseHelper mDatabaseHelper;

    private String selectedName;
    private int selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        btnSave = findViewById(R.id.savebtn);
        btnDelete = findViewById(R.id.deletebtn);
        editable_item = findViewById(R.id.editableitem);
        mDatabaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();

        selectedId = receivedIntent.getIntExtra("id",-1);
        selectedName = receivedIntent.getStringExtra("name");

        editable_item.setText(selectedName);
        
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,selectedId,selectedName);
                    Intent intent = new Intent(getApplicationContext(), SaveTaskActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(editDataActivity.this, "you must enter a name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteName(selectedId,selectedName);
                editable_item.setText("");
                Toast.makeText(editDataActivity.this, "removed from database", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SaveTaskActivity.class);
                startActivity(intent);

            }
        });
    }
}