package com.example.itc.disease;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.itc.disease.db.DB2;

import static com.example.itc.disease.R.id.spinner;

public class rekordActivity extends AppCompatActivity {
    ImageButton Back_button;
    Button Inter;
    DatePicker datePicker;
    ArrayAdapter<CharSequence> adspin;
    Spinner spin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekord);
        final DB2 dbHelper = new DB2(getApplicationContext(), "User.db", null, 1);

        datePicker = (DatePicker)findViewById(R.id.datePicker);
        spin = (Spinner)findViewById(spinner);

        adspin = ArrayAdapter.createFromResource(this, R.array.disease_name, android.R.layout.simple_spinner_item);
        adspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setPrompt("병명");
        spin.setAdapter(adspin);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

            }
            public void onNothingSelected(AdapterView<?> parent){}

        });


        Inter = (Button)findViewById(R.id.inter);
        Inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rekordActivity.this, rekord2Activity.class);
                startActivity(intent);
                final int year = datePicker.getYear();
                final int month = datePicker.getMonth()+1;
                final int day = datePicker.getDayOfMonth();
                final String select_name = spin.getSelectedItem().toString();
                final String Date = year + "년" + month + "월" + day + "일";
                Log.d("intentput : ",""+datePicker.getYear());
                dbHelper.insert3(select_name,Date);

            }
        });

        Back_button = (ImageButton)findViewById(R.id.back_button);
        Back_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(rekordActivity.this, ChoiceActivity.class);
                startActivity(intent);

            }
        });



    }
}
