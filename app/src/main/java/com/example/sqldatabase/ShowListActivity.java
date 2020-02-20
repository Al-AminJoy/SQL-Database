package com.example.sqldatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListAdapter listAdapter;
    private DatabaseHelper databaseHelper;
    private ArrayList<ModelClass> listItem=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        recyclerView=findViewById(R.id.rvShowList);
        databaseHelper=new DatabaseHelper(this);
        //SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        loadData();
    }

    private void loadData() {
        Cursor cursor=databaseHelper.ShowData();
        if (cursor.getCount()==0)
        {

        }
        else
        {
            while (cursor.moveToNext())
            {
                String Id=cursor.getString(0);
                String Name=cursor.getString(1);
                String Age=cursor.getString(2);
                String Gender=cursor.getString(3);
                ModelClass modelClass=new ModelClass(Id,Name,Age,Gender);
                listItem.add(modelClass);
            }
            listAdapter=new ListAdapter(getApplicationContext(),listItem);
            recyclerView.setAdapter(listAdapter);

        }

    }
}
