package com.example.secondtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Disagree extends AppCompatActivity {

    private Button shutdown;
    private TextView disagreee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disagree);

        shutdown=findViewById(R.id.button9);
        shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        disagreee=findViewById(R.id.textView7);
        disagreee.setText("Thanks for using Behavoiur App!" +
                "Have a good day!");
    }
}
