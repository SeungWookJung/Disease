package com.example.itc.disease;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class seoljeongActivity extends AppCompatActivity {
    ImageButton Back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seoljeong);

        Back_button = (ImageButton)findViewById(R.id.back_button);
        Back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seoljeongActivity.this, ChoiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
