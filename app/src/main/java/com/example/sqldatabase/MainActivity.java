package com.example.sqldatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    private EditText etName,etAge,etGender;
    private Button btSubmit,btShow,btShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

        etName=findViewById(R.id.etnameId);
        etAge=findViewById(R.id.etAgeId);
        etGender=findViewById(R.id.etGenderId);
        btSubmit=findViewById(R.id.btSubmitId);
        btShow=findViewById(R.id.btShowId);
        btShowList=findViewById(R.id.btShowListId);

        btSubmit.setOnClickListener(this);
        btShow.setOnClickListener(this);
        btShowList.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String name=etName.getText().toString();
        String age=etAge.getText().toString();
        String gender=etGender.getText().toString();
        if (v.getId()==R.id.btSubmitId)
        {
          long rowId=databaseHelper.insertData(name,age,gender);
          if (rowId==-1)
          {
              Toast.makeText(getApplicationContext(),"Unsuccessful",Toast.LENGTH_SHORT).show();
          }
          else
          {
              Toast.makeText(getApplicationContext(),"Successfully Row "+rowId+"Inserted",Toast.LENGTH_SHORT).show();
          }
        }
        else if (v.getId()==R.id.btShowId)
        {
            Cursor cursor=databaseHelper.ShowData();
            if (cursor.getCount()==0)
            {

                return;
            }
            else {
                StringBuffer stringBuffer=new StringBuffer();
                while (cursor.moveToNext())
                {
                    stringBuffer.append("Id: "+cursor.getString(0)+"\n");
                    stringBuffer.append("Name : "+cursor.getString(1)+"\n");
                    stringBuffer.append("Age : "+cursor.getString(2)+"\n");
                    stringBuffer.append("Gender : "+cursor.getString(3)+"\n");
                }
                showDialogue("DataList",stringBuffer.toString());

            }
        }
        else if (v.getId()==R.id.btShowListId)
        {
            Intent intent=new Intent(MainActivity.this,ShowListActivity.class);
            startActivity(intent);
        }


    }

    private void showDialogue(String dataList, String StringValues) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(dataList).setMessage(StringValues).setCancelable(true);
        builder.show();

    }
}
