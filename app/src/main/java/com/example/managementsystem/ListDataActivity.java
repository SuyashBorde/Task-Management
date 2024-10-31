package com.example.managementsystem;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String Tag = "ListDataActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);



            listView = findViewById(R.id.Listview);
            mDatabaseHelper = new DatabaseHelper(this);

            populateListView();


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

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String name = parent.getItemAtPosition(position).toString();

                    Log.d(TAG,"onItemClick: You Clicked on "+ name);

                    Cursor data = mDatabaseHelper.getItemid(name);
                    int itemID = -1;
                    while (data.moveToNext()){
                        itemID = data.getInt(0);
                    }
                    if(itemID > -1){
                        Log.d(TAG,"onItemClick: The ID is: "+ itemID);
                        Intent newpage = new Intent(ListDataActivity.this,editDataActivity.class);
                        newpage.putExtra("id",itemID);
                        newpage.putExtra("name",name);
                        startActivity(newpage);
                    }
                    else {
                        Toast.makeText(ListDataActivity.this, "No ID associated with that name", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

}
