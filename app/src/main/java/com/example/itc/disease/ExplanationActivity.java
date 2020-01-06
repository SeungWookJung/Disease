package com.example.itc.disease;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.itc.disease.db.DB2;


public class ExplanationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanation);


        Intent intent = getIntent();
        String idx = intent.getExtras().getString("idx");

        Log.i("test", "idx : " + idx);

        DB2 dbHelper = new DB2(getApplicationContext(), "disease.db", null, 1);
        Cursor cursor = dbHelper.getDiseaseForIdx(idx);
        TextView textview = (TextView)findViewById(R.id.info);

        try {
            if (cursor.moveToFirst()) {
                do {
                    String idx2 = cursor.getString(0);
                    String name = cursor.getString(1);
                    String e_name = cursor.getString(2);
                    String info = cursor.getString(3);
                    textview.setText(info);
                    Log.i("test", idx2 + " : " + name + " : " + e_name + " : " + info);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
//            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

    }
}
