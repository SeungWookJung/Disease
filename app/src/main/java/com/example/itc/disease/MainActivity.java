package com.example.itc.disease;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


//import com.example.itc.disease.db.DBHelper;

public class MainActivity extends AppCompatActivity {
    Button Login_button;
    EditText id;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id =(EditText)findViewById(R.id.Id);
        password=(EditText)findViewById(R.id.Password);






        Login_button = (Button)findViewById(R.id.login_button);
        Login_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), ChoiceActivity.class);
                startActivity(intent);

            }
        });


      }
}
