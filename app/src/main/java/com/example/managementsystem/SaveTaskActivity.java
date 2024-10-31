package com.example.managementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SaveTaskActivity extends AppCompatActivity {

    private static final String tag = "SaveTaskActivity";

    DatabaseHelper mdatabaseHelper;
    private EditText taskEditText;
    private Button AddButton, ViewBtn;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_task);

            taskEditText = findViewById(R.id.taskEditText);
            AddButton = findViewById(R.id.Add);
            ViewBtn = findViewById(R.id.ViewData);
            btn = findViewById(R.id.empdata);
            mdatabaseHelper = new DatabaseHelper(this);

            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManagerPage.class);
                startActivity(intent);
            }
            });

            AddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newEntry = taskEditText.getText().toString();
                    if (taskEditText.length()!=0){
                        addData(newEntry);
                        taskEditText.setText("");
                    }
                    else {
                        ToastMessage("you must put something in text feild!");
                    }
                }
            });

            ViewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SaveTaskActivity.this,ListDataActivity.class);
                    startActivity(intent);
                }
            });




    }

        public void addData(String newEntry){
            boolean insertData = mdatabaseHelper.addData(newEntry);
            if (insertData){
                ToastMessage("Data Successfully inserted!");
            }else{
                ToastMessage("Something Went Wrong");
            }
        }
        private void ToastMessage(String message){
            Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
        }

}
