package com.example.itc.disease;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.itc.disease.db.DB2;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ChoiceActivity extends AppCompatActivity {
    ImageButton imgbtn1;
    ImageButton imgbtn2;
    ImageButton imgbtn3;
    ImageButton option_Button;

    DB2 dbHelper;

    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd");
    String formatDate = sdfNow.format(date);

    TextView dateNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);


        dateNow = (TextView) findViewById(R.id.dateNow);
        dateNow.setText(formatDate);



        imgbtn1 = (ImageButton)findViewById(R.id.imageButton1);
        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, listActivity.class);
                startActivity(intent);
            }
        });

        imgbtn2 = (ImageButton)findViewById(R.id.imageButton2);
        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, rekord2Activity.class);
                startActivity(intent);
            }
        });

        imgbtn3 = (ImageButton)findViewById(R.id.imageButton3);
        imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

    }

}
