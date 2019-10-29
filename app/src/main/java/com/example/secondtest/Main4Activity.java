package com.example.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {
    TextView text;
    Button button;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        button=findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main4Activity.this, AgreementPage.class);
                startActivity(intent);
            }
        });

        title=findViewById(R.id.textView5);
        title.setText("Behaviour");

        text=findViewById(R.id.textView4);
        text.setText("Welcome to use the Behaviuour which is a light weight machine learning Android application." +
                "This is first version include several buttons to image to different function which user may use in " +
                "different applications.");

    }
}
