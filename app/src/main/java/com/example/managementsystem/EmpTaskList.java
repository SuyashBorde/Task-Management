package com.example.managementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EmpTaskList extends AppCompatActivity {
    private static final String Tag = "EmpTaskList";
    DatabaseHelper mDatabaseHelper;
    private ListView listView;
    private Button profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_task_list);

        listView = findViewById(R.id.listview1);
        profile = findViewById(R.id.ProfileBtn);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EmpProfilePage.class);
                startActivity(intent);
            }
        });
    }

    private void populateListView() {

        Log.d(Tag,"Populated List View: Displaying Data in ListView");
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listdata = new ArrayList<>();
        while (data.moveToNext()){
            listdata.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listdata);
        listView.setAdapter(adapter);
    }
}