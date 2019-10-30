package com.example.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AgreementPage extends AppCompatActivity {

    private Button agree;
    private Button diagree;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement_page);

        agree=findViewById(R.id.button4);
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgreementPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        diagree=findViewById(R.id.button10);
        diagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgreementPage.this, Disagree.class);
                startActivity(intent);
            }
        });

        text=findViewById(R.id.textView6);
        text.setText("This is a testing software on users' behaviour analysis." +
                "The data will only collect the click rate abd function used rate to suggest one of the function user may not use." +
                "The data is collected only for research. ");

    }
}
